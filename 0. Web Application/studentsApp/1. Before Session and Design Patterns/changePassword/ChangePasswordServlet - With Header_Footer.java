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

public class ChangePasswordServlet extends HttpServlet 
{
	RequestDispatcher dispatcher = null;
	
	@Override
	protected void doPost(HttpServletRequest req, 
						  HttpServletResponse resp)
	throws ServletException, IOException 
	{
		// Get the Form Data
		String regnoVal = req.getParameter("regno");
		String currPassVal = req.getParameter("currPass");
		String newPassVal = req.getParameter("newPass");
		String reNewPassVal = req.getParameter("reNewPass");

		StringBuffer sb = new StringBuffer();
		
		// Check New Password & Retype New Password is Same
		if (newPassVal.equals(reNewPassVal)) {
			// New Password = Retype New Password; Hence Update
			Connection con = null;
			PreparedStatement pstmt = null;
			try 
			{
				//1. Load the Driver
				Class.forName("com.mysql.jdbc.Driver").newInstance();

				//2. Get the DB Connection via Driver 
				String dbUrl = "jdbc:mysql://localhost:3306/studentsapp_db?user=j2ee&password=j2ee";
				con = DriverManager.getConnection(dbUrl);

				//3. Issue SQL Queries via Connection 
				String query = " update student_otherinfo "
								+ " set password=? " 
								+ " where Regno=? "
								+ " and password=? ";
				
				System.out.println("Query : "+query);
				
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, newPassVal);
				pstmt.setInt(2, Integer.parseInt(regnoVal));
				pstmt.setString(3, currPassVal);
				int count = pstmt.executeUpdate();
				
				//4. Process the Results returned by SQL Queries
				sb.append("<html> ");
				sb.append("<body> ");
				
				if (count > 0) {
					sb.append("<font color=\"green\">"); 
					sb.append("Successfully Changed the Password ...");
					sb.append("</font>");
				} else {
					sb.append("<font color=\"red\">"); 
					sb.append("Reg. No. & Current Password Doesn't Match !!!");
					sb.append("</font>");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				//5. Close All JDBC Objects
				try 
				{
					if (con != null) {
						con.close();
					}
					if (pstmt != null) {
						pstmt.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} else {
			sb.append("<font color=\"red\">"); 
			sb.append("New Password & Re-type New Password Doesn't Match !!!");
			sb.append("</font>");
		}
		
		sb.append("</body>");
		sb.append("</html>");
		
		//Send the response to Browser
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();

		dispatcher=req.getRequestDispatcher("header");
		dispatcher.include(req, resp);
		
		out.println(sb.toString());
		
		dispatcher=req.getRequestDispatcher("ChangePassword.html");
		dispatcher.include(req, resp);
		
		dispatcher=req.getRequestDispatcher("Footer.html");
		dispatcher.include(req, resp);
		
	}//End of doPost
}//End of Class
