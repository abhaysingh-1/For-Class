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

public class SearchByStudentInfoServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, 
						 HttpServletResponse resp) 
	throws ServletException, IOException {
		
		//I. Get the Form Data
		String nameVal = req.getParameter("name");
		String mockVal = req.getParameter("mock");
		String courseVal = req.getParameter("course");
		String mobileVal = req.getParameter("mobile");
		String cityVal = req.getParameter("city");
		String pincodeVal = req.getParameter("pincode");
		
		/*
		 * II. Search for Data/Records in 
		 * BECME1820_DB DataBase
		 */
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			//1. Load the Driver
			//Driver Class : com.mysql.jdbc.Driver
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			
			//2. Get the DB Connection via Driver
			String dbUrl = "jdbc:mysql://localhost:3306/studentsapp_db?user=j2ee&password=j2ee";
			con = DriverManager.getConnection(dbUrl);
			
			//3. Issue SQL Queries via Connection
			StringBuilder query = new StringBuilder();
			query.append("select * from student_info si,"); 
			query.append("student_addressinfo sai,");
			query.append("student_courseinfo sci,"); 
			query.append("course_info ci");
			query.append("where si.regno = sai.regno");
			query.append("and sci.course_id=ci.course_id");
			query.append("and sci.regno=si.regno");
			query.append("and"); 
			query.append("(si.firstname like '%pra%'");
			query.append("or si.middlename like '%pra%'");
			query.append("or si.lastname like '%pra%')");
			query.append("and sai.city like '%bangalore%'");
			query.append("and sai.pincode = 560008");
			query.append("and sci.mock_rating like '%1*%'");
			query.append("and ci.course_name like '%java%'");
			
			System.out.println("Query : "+query.toString());
			pstmt = con.prepareStatement(query.toString());
//			pstmt.setInt(1, Integer.parseInt(regnoVal));
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
			
		} catch (Exception e) {
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








