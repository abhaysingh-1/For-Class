package com.jspiders.studentsapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateProfileServlet extends HttpServlet
{
	RequestDispatcher dispatcher =null;
	
	@Override
	protected void doPost(HttpServletRequest req, 
						  HttpServletResponse resp)
	throws ServletException, IOException 
	{
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		dispatcher=req.getRequestDispatcher("Header.html");
		dispatcher.include(req, resp);
		
		//I. Get the Form Data
		String regnoVal = req.getParameter("regno");
		String fnmVal = req.getParameter("fnm");
		String mnmVal = req.getParameter("mnm");
		String lnmVal = req.getParameter("lnm");
		
		String gfnmVal = req.getParameter("gfnm");
		String gmnmVal = req.getParameter("gmnm");
		String glnmVal = req.getParameter("glnm");
		
		String isAdminVal = req.getParameter("isAdmin");
		String passVal = req.getParameter("pass");
		
		//II. Store the Form Data into DB
		Connection con = null;
		PreparedStatement pstmt = null;
		
		out.print("<html>");
		out.print("<body>");
		
		try 
		{
			//1. Load the Driver
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			
			//2. Get the DB Connection via Driver 
			String dbUrl = "jdbc:mysql://localhost:3306/studentsapp_db?user=j2ee&password=j2ee";
			con = DriverManager.getConnection(dbUrl);
			
			//3. Issue SQL Queries via Connection 
			String query1 = "insert into students_info "
				      		+ " values (?, ?, ?, ?) ";
			
			String query2 = "insert into guardian_info "
				      		+ " values (?, ?, ?, ?) ";
			
			String query3 = "insert into students_otherinfo "
		      				+ " values (?, ?, ?) ";
		
			pstmt = con.prepareStatement(query1);
			pstmt.setInt(1, Integer.parseInt(regnoVal) );
			pstmt.setString(2, fnmVal);
			pstmt.setString(3, mnmVal);
			pstmt.setString(4, lnmVal);
			pstmt.executeUpdate();
			pstmt.close();
			
			pstmt = con.prepareStatement(query2);
			pstmt.setInt(1, Integer.parseInt(regnoVal) );
			pstmt.setString(2, gfnmVal);
			pstmt.setString(3, gmnmVal);
			pstmt.setString(4, glnmVal);
			pstmt.executeUpdate();
			pstmt.close();
			
			pstmt = con.prepareStatement(query3);
			pstmt.setInt(1, Integer.parseInt(regnoVal) );
			pstmt.setString(2, isAdminVal);
			pstmt.setString(3, passVal);
			pstmt.executeUpdate();
			
			//4. Process the Results returned by SQL Queries
			
			out.print("<font color=\"green\">"); 
			out.print("Successfully Created the Profile ...");
			out.print("</font>");
			
		} catch (Exception e) {
			out.print("<font color=\"red\">"); 
			out.print("Unable to Create the Profile !!!");
			out.print("</font>");
			
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
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}//End of outer try-catch
		
		out.print("<BR><BR>");
		dispatcher=req.getRequestDispatcher("CreateProfile.html");
		dispatcher.include(req, resp);
		
		dispatcher=req.getRequestDispatcher("Footer.html");
		dispatcher.include(req, resp);
		
		out.print("</body>");
		out.print("</html>");
		
	}//End of doPost
}//End of Class
