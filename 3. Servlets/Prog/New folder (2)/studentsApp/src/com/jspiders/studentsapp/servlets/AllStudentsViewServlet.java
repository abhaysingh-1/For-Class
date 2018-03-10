package com.jspiders.studentsapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AllStudentsViewServlet extends HttpServlet 
{
	RequestDispatcher dispatcher =null;
	
	@Override
	protected void doGet(HttpServletRequest req, 
						 HttpServletResponse resp)
	throws ServletException, IOException 
	{
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		//II. Validate the Session
		HttpSession session = req.getSession(false);
		 
		if(session==null){
			//Invalid Session; Generate Login Page
			out.print("<font color=\"red\">");
			out.println("In-Valid Session !!! Pls Login ...");
			out.print("</font>");
			
			dispatcher=req.getRequestDispatcher("Login.html");
			dispatcher.include(req, resp);
		}else{
			//Valid Session; Generate Proper Response

			//Get the Query String Info
			String nextRegNoVal = req.getParameter("nextRegNo");
			int fromRegNo = 0;
			int toRegNo = 0;
			int rows = 4;
			
			if(nextRegNoVal==null){
				fromRegNo=1;
				toRegNo=5;
			}else{
				fromRegNo=Integer.parseInt(nextRegNoVal);
				toRegNo=fromRegNo+rows;
			}
			// Interact with DB to get the data
		    Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			StringBuffer sb = new StringBuffer();
			
			
			try 
			{
				//1. Load the Driver
				//Driver Class : com.mysql.jdbc.Driver
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				
				//2. Get the DB Connection via Driver 
				String dbUrl = "jdbc:mysql://localhost:3306/studentsapp_db?user=j2ee&password=j2ee";
				con = DriverManager.getConnection(dbUrl);
				
				//3. Issue SQL Queries via Connection 
				String query = " select * from "
						  	  +" students_info si, "
						  	  +" guardian_info gi "
						  	  +" where si.regno = gi.regno "
						  	  +" and si.regno between ? and ? ";
				
				System.out.println("Query : "+query);
				
				pstmt = con.prepareStatement(query);
				pstmt.setInt(1, fromRegNo);
				pstmt.setInt(2, toRegNo);
				rs = pstmt.executeQuery();
				
				dispatcher=req.getRequestDispatcher("Header.html");
				dispatcher.include(req, resp);

				//4. Process the Results returned by SQL Queries
				out.print("<html> ");
				out.print("<body> ");
				out.print("<table>");
				out.print("<tr bgcolor=\"green\">");
				out.print("<td>Reg. No.</td>     ");
				out.print("<td>First Name</td>   ");
				out.print("<td>Middle Name</td>  ");
				out.print("<td>Last Name</td>    ");
				out.print("<td>G First Name</td> ");
				out.print("<td>G Middle Name</td>");
				out.print("<td>G Last Name</td>  ");
				out.print("</tr>");
				
				while(rs.next())
				{
					String url = "./studentSearch?regno="+rs.getInt("si.regno");
					
					out.print("<tr> ");
					out.print("<td><a href=\""+url+"\">"+rs.getInt("si.regno")+"</a></td>  ");
					out.print("<td>"+rs.getString("si.firstname")+"</td>");
					out.print("<td>"+rs.getString("si.middlename")+"</td> ");
					out.print("<td>"+rs.getString("si.lastname")+"</td>");
					out.print("<td>"+rs.getString("gi.gfirstname")+"</td>");
					out.print("<td>"+rs.getString("gi.gmiddlename")+"</td> ");
					out.print("<td>"+rs.getString("gi.glastname")+"</td>");
					out.print("</tr>");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally{
				//5. Close All JDBC Objects
				try 
				{
					if(con!=null){
						con.close();
					}
					if(pstmt!=null){
						pstmt.close();
					}
					if(rs!=null){
						rs.close();
					}
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}//End of outer try-catch block
			
			out.print("</table>");
			out.print("</body>");
			out.print("</html>");
			
			out.print("<BR><BR>");
			String nextUrl = "./allStudentsView?nextRegNo="+(toRegNo+1);
			out.print("<a href=\""+nextUrl+"\"> Next >> </a>");
			
			dispatcher=req.getRequestDispatcher("Footer.html");
			dispatcher.include(req, resp);
		}
		
	}//End of doGet
}//End of Class





