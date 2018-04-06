package com.jspiders.studentsapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Driver;

public class StudentSearchServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, 
						 HttpServletResponse resp) 
	throws ServletException, IOException {
		
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
			
			//4. Process the results returned by SQL Queries
			String htmlResp;
			
			if(rs.next()){
				int regNum = rs.getInt("si.regno");
				String fNM = rs.getString("si.firstname");
				String mNM = rs.getString("si.middlename");
				String lNM = rs.getString("si.lastname");
				String gfNM = rs.getString("gi.gfirstname");
				String gmNM = rs.getString("gi.gmiddlename");
				String glNM = rs.getString("gi.glastname");
				
				htmlResp = "<html>"
							+"<body>"
							+"<table>"
							+"<tr bgcolor=\"green\">"
							+"<td>Reg. No.</td>"
							+"<td>First Name</td>"
							+"<td>Middle Name</td>"
							+"<td>Last Name</td>"
							+"<td>G First Name</td>"
							+"<td>G Middle Name</td>"
							+"<td>G Last Name</td>"
							+"</tr>"
							+"<tr>"
							+"<td>"+regNum+"</td>"
							+"<td>"+fNM+"</td>"
							+"<td>"+mNM+"</td>"
							+"<td>"+lNM+"</td>"
							+"<td>"+gfNM+"</td>"
							+"<td>"+gmNM+"</td>"
							+"<td>"+glNM+"</td>"
							+"</tr>"
							+"</table>"
							+"</body>"
							+"</html>";
			}else{
				htmlResp = "<html>"
						   +"<body>"
						   +"<h4>"
						   +"<font color=\"red\">"
						   +"Reg. No. NOT Present !!!"
						   +"</font>"
						   +"</h4>"
						   +"</body>"
						   +"</html>";
			}
			
			//Send the response to Browser
			resp.setContentType("text/html");
			PrintWriter out = resp.getWriter();
			out.println(htmlResp);
			
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








