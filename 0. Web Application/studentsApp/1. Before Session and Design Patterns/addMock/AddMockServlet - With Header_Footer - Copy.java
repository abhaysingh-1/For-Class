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

import com.mysql.jdbc.Driver;

public class AddMockServlet extends HttpServlet{

	RequestDispatcher dispatcher = null;

	@Override
	protected void doPost(HttpServletRequest req, 
						 HttpServletResponse resp) 
	throws ServletException, IOException {
		
		//To Send the response to Browser
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		dispatcher=req.getRequestDispatcher("header");
		dispatcher.include(req, resp);

		out.println("<html>");
		out.println("<body>");

		//I. Get the Form Data
		String regnoVal = req.getParameter("regno");
		String courseIdVal = req.getParameter("courseId");
		String mockVal = req.getParameter("mock");
		
		/*
		 * II. Insert the above Form Data into 
		 * BECME1820_DB DataBase
		 */
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			//1. Load the Driver
			//Driver Class : com.mysql.jdbc.Driver
			Driver driverRef = new Driver();
			DriverManager.registerDriver(driverRef);
			
			//2. Get the DB Connection via Driver
			String dbUrl = "jdbc:mysql://localhost:3306/studentsapp_db?user=j2ee&password=j2ee";
			con = DriverManager.getConnection(dbUrl);
			
			//3. Issue SQL Queries via Connection
			String query = 	" insert into student_courseinfo "
		      				+ " values (?, ?, ?) ";
			
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(regnoVal));
			pstmt.setInt(2, Integer.parseInt(courseIdVal));
			pstmt.setString(3, mockVal);
			pstmt.executeUpdate();
			
			//4. Process the results returned by SQL Queries
			out.println("<font color=\"green\">");
			out.println("Successfully Added Mock Rating for Reg. No. :- "+regnoVal+" and for Course ID "+courseIdVal);
			out.println("</font>");
			
		} catch (SQLException e) {
			out.println("<font color=\"red\">");
			out.println("Unable to Add Mock Rating!!!");
			out.println("<BR> Looks like Mock rating already exists for "+regnoVal+" and for Course ID "+courseIdVal+"Error is : ");
			out.println("<BR> Error is : ");
			out.println(e.getMessage());
			out.println("</font>");
		} finally{
			//5. Close ALL JDBC Objects
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
		}//End of outer try-catch
		
		out.println("</body>");
		out.println("</html>");

		dispatcher=req.getRequestDispatcher("AddMock.html");
		dispatcher.include(req, resp);
		
		dispatcher=req.getRequestDispatcher("Footer.html");
		dispatcher.include(req, resp);
		
	}//End of doGet
	
}//End of Class








