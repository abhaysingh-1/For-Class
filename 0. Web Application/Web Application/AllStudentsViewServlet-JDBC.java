package com.jspiders.studentsapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AllStudentsViewServlet extends HttpServlet 
{
	RequestDispatcher dispatcher = null;
	
	@Override
	protected void doGet(HttpServletRequest req, 
						HttpServletResponse resp)
	throws ServletException, IOException 
	{
		resp.setContentType("text/html");
	    PrintWriter out = resp.getWriter();
	    
		dispatcher = req.getRequestDispatcher("Header.html");
	    dispatcher.include(req, resp);
	    
	    Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try 
		{
			//1. Load the Driver
			//Driver Class : com.mysql.jdbc.Driver
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			
			//2. Get the DB Connection via Driver 
			String dbUrl = "jdbc:mysql://localhost:3306/studentsapp_db?user=j2ee&password=j2ee";
			con = DriverManager.getConnection(dbUrl);
			
			//3. Issue SQL Queries via Connection 
			String query = " select * from "+
							" students_info si, "+
							" guardian_info gi, "+
							" students_otherinfo soi "+
							" where si.regno = gi.regno "+
							" and si.regno = soi.regno ";
			
			System.out.println("Query : "+query);
			
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			
			//4. Process the Results returned by SQL Queries
			out.println("<html> ");
			out.println("<body> ");
			out.println("<table>");
			out.println("<tr bgcolor=\"green\">");
			out.println("<td>Reg. No.</td>     ");
			out.println("<td>First Name</td>   ");
			out.println("<td>Middle Name</td>  ");
			out.println("<td>Last Name</td>    ");
			out.println("<td>G First Name</td> ");
			out.println("<td>G Middle Name</td>");
			out.println("<td>G Last Name</td>  ");
			out.println("</tr>");
			
			while(rs.next())
			{
				out.println("<tr> ");
				out.println("<td>"+rs.getInt("si.regno")+"</td>  ");
				out.println("<td>"+rs.getString("si.firstname")+"</td>");
				out.println("<td>"+rs.getString("si.middlename")+"</td> ");
				out.println("<td>"+rs.getString("si.lastname")+"</td>");
				out.println("<td>"+rs.getString("gi.gfirstname")+"</td>");
				out.println("<td>"+rs.getString("gi.gmiddlename")+"</td> ");
				out.println("<td>"+rs.getString("gi.glastname")+"</td>");
				out.println("</tr>");
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
				if(stmt!=null){
					stmt.close();
				}
				if(rs!=null){
					rs.close();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}//End of outer try-catch block
		
		out.println("</table>    ");
		out.println("</body>     ");
		out.println("</html>     ");
	    
	    dispatcher = req.getRequestDispatcher("Footer.html");
	    dispatcher.include(req, resp);
	}//End of doGet
}//End of Class





