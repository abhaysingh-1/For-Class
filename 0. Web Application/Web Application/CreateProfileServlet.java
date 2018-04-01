package com.jspiders.studentsapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateProfileServlet 
extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException 
	{
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
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
		CallableStatement cstmt = null;
		
		try 
		{
			//1. Load the Driver
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			
			//2. Get the DB Connection via Driver 
			String dbUrl = "jdbc:mysql://localhost:3306/BECM4_DB?user=j2ee&password=j2ee";
			con = DriverManager.getConnection(dbUrl);
			
			//3. Issue SQL Queries via Connection 
			String query = "call studentUpSert2(?,?,?,?,?,?,?,?,?)";
			cstmt = con.prepareCall(query);
			cstmt.setInt(1, Integer.parseInt(regnoVal) );
			cstmt.setString(2, fnmVal);
			cstmt.setString(3, mnmVal);
			cstmt.setString(4, lnmVal);
			cstmt.setString(5, gfnmVal);
			cstmt.setString(6, gmnmVal);
			cstmt.setString(7, glnmVal);
			cstmt.setString(8, isAdminVal);
			cstmt.setString(9, passVal);
			cstmt.executeUpdate();
			
			//4. Process the Results returned by SQL Queries
			out.println("<font color=\"green\">"); 
			out.println("Successfully Created the Profile ...");
			out.println("</font>");
			
		} catch (Exception e) {
			out.println("<font color=\"red\">"); 
			out.println("Unable to Create the Profile !!!");
			out.println("</font>");
			e.printStackTrace();
		} finally{
			//5. Close All JDBC Objects
			try 
			{
				if(con!=null){
					con.close();
				}
				if(cstmt!=null){
					cstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}//End of outer try-catch
	}//End of doPost
}//End of Class
