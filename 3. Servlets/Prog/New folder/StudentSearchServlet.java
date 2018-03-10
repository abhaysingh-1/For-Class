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

public class StudentSearchServlet extends HttpServlet{

	RequestDispatcher dispatcher =null;
	
	@Override
	protected void doGet(HttpServletRequest req, 
						 HttpServletResponse resp) 
	throws ServletException, IOException {
		
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		//Get the Query String Info
		String regnoVal = req.getParameter("regno");
		
		//Check this Reg. No. exists in BECM19_DB DataBase
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
			String query = " select * from "
					       + "   students_info si, "
					       + "   guardian_info gi "
						   + " where si.regno=gi.regno "
					       + " and si.regno=? ";
			
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(regnoVal));
			rs = pstmt.executeQuery();
			
			dispatcher=req.getRequestDispatcher("Header.html");
			dispatcher.include(req, resp);
			
			dispatcher=req.getRequestDispatcher("Search.html");
			dispatcher.include(req, resp);
			out.print("<BR><BR>");
			
			//4. Process the results returned by SQL Queries
			if(rs.next()){
				int regNum = rs.getInt("si.regno");
				String fNM = rs.getString("si.firstname");
				String mNM = rs.getString("si.middlename");
				String lNM = rs.getString("si.lastname");
				String gfNM = rs.getString("gi.gfirstname");
				String gmNM = rs.getString("gi.gmiddlename");
				String glNM = rs.getString("gi.glastname");
				
				out.print("<html>");
				out.print("<body>");
				out.print("<table>");
				out.print("<tr bgcolor=\"green\">");
				out.print("<td>Reg. No.</td>");
				out.print("<td>First Name</td>");
				out.print("<td>Middle Name</td>");
				out.print("<td>Last Name</td>");
				out.print("<td>G First Name</td>");
				out.print("<td>G Middle Name</td>");
				out.print("<td>G Last Name</td>");
				out.print("</tr>");
				out.print("<tr>");
				out.print("<td>"+regNum+"</td>");
				out.print("<td>"+fNM+"</td>");
				out.print("<td>"+mNM+"</td>");
				out.print("<td>"+lNM+"</td>");
				out.print("<td>"+gfNM+"</td>");
				out.print("<td>"+gmNM+"</td>");
				out.print("<td>"+glNM+"</td>");
				out.print("</tr>");
				out.print("</table>");
				out.print("</body>");
				out.print("</html>");
				
			}else{
				out.print("<html>");
				out.print("<body>");
				out.print("<h4>");
				out.print("<font color=\"red\">");
				out.print("Reg. No. NOT Present !!!");
				out.print("</font>");
				out.print("</body>");
				out.print("</html>");
			}
			
			dispatcher=req.getRequestDispatcher("Footer.html");
			dispatcher.include(req, resp);
			
		} catch (SQLException e) {
			e.printStackTrace();
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
	}//End of doGet
	
}//End of Class








