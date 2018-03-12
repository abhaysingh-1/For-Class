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
	@Override
	protected void doGet(HttpServletRequest req, 
						 HttpServletResponse resp)
	throws ServletException, IOException 
	{
		// Interact with DB to get the data
	    Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		StringBuffer sb = new StringBuffer("");
		
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
						   " guardian_info gi "+
						   " where si.regno=gi.regno ";
			
			System.out.println("Query : "+query);
			
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			
			//4. Process the Results returned by SQL Queries
			sb.append("<html> ");
			sb.append("<body> ");
			sb.append("<table>");
			sb.append("<tr bgcolor=\"green\">");
			sb.append("<td>Reg. No.</td>     ");
			sb.append("<td>First Name</td>   ");
			sb.append("<td>Middle Name</td>  ");
			sb.append("<td>Last Name</td>    ");
			sb.append("<td>G First Name</td> ");
			sb.append("<td>G Middle Name</td>");
			sb.append("<td>G Last Name</td>  ");
			sb.append("</tr>");
			
			while(rs.next())
			{
				String url = "http://localhost:8080/studentsApp/studentSearch?regno="+rs.getInt("si.regno");
				
				sb.append("<tr> ");
				sb.append("<td><a href=\""+url+"\">"+rs.getInt("si.regno")+"</a></td>  ");
				sb.append("<td>"+rs.getString("si.firstname")+"</td>");
				sb.append("<td>"+rs.getString("si.middlename")+"</td> ");
				sb.append("<td>"+rs.getString("si.lastname")+"</td>");
				sb.append("<td>"+rs.getString("gi.gfirstname")+"</td>");
				sb.append("<td>"+rs.getString("gi.gmiddlename")+"</td> ");
				sb.append("<td>"+rs.getString("gi.glastname")+"</td>");
				sb.append("</tr>");
			}//End of while
			
			sb.append("</table>");
			sb.append("</body>");
			sb.append("</html>");
			
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
		
		//Send the response to Browser
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println(sb.toString());
	    
	}//End of doGet
}//End of Class





