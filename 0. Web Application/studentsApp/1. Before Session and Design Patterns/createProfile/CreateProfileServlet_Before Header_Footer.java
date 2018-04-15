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

import com.jspiders.studentsapp.util.StudentsAppUtil;

public class CreateProfileServlet extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, 
						  HttpServletResponse resp)
	throws ServletException, IOException 
	{
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		//I. Get the Form Data
		//Profile Related Info
		String regnoVal = req.getParameter("regno");
		String passVal = req.getParameter("pass");
		String isAdminVal = req.getParameter("isAdmin");
		String emailVal = req.getParameter("email");
		String dobVal = req.getParameter("dob");

		//Course Related Info
		String[] courseVals = req.getParameterValues("course");
		
		//Student Basic Info ...
		String fnmVal = req.getParameter("fnm");
		String mnmVal = req.getParameter("mnm");
		String lnmVal = req.getParameter("lnm");
		String phoneVal = req.getParameter("phone");
		
		//Guardian Related Info
		String gfnmVal = req.getParameter("gfnm");
		String gmnmVal = req.getParameter("gmnm");
		String glnmVal = req.getParameter("glnm");
		String gphoneVal = req.getParameter("gphone");
		
		//Student Permanent Address Info
		String pre_addr1 = req.getParameter("pre.addr1");
		String pre_addr2 = req.getParameter("pre.addr2");
		String pre_landmark = req.getParameter("pre.landmark");
		String pre_city = req.getParameter("pre.city");
		String pre_pincode = req.getParameter("pre.pincode");
		
		//Student Permanent Address Info
		String per_addr1 = req.getParameter("per.addr1");
		String per_addr2 = req.getParameter("per.addr2");
		String per_landmark = req.getParameter("per.landmark");
		String per_city = req.getParameter("per.city");
		String per_pincode = req.getParameter("per.pincode");

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
			String query1 = " insert into student_info "
				      		+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?) ";
			
			String query2 = " insert into guardian_info "
				      		+ " values (?, ?, ?, ?, ?) ";
			
			String query3 = " insert into student_otherinfo (regno, isadmin, password) "
		      				+ " values (?, ?, ?) ";

			String query4 = " insert into student_addressinfo "
							+ " values (?, ?, ?, ?, ?, ?, ?) ";
			
			String query5 = " insert into student_courseinfo (regno, course_id) "
							+ " values (?, ?) ";

			pstmt = con.prepareStatement(query1);
			pstmt.setInt(1, Integer.parseInt(regnoVal) );
			pstmt.setString(2, fnmVal);
			pstmt.setString(3, mnmVal);
			pstmt.setString(4, lnmVal);
			pstmt.setString(5, emailVal);
			pstmt.setLong(6, Long.parseLong(phoneVal));

			String[] dobVals = dobVal.split("-");
			String birthDay = dobVals[2];
			String birthMonth = StudentsAppUtil.getInstance().getMonthName(dobVals[1]);
			String birthYear = dobVals[0];
			
			pstmt.setInt(7, Integer.parseInt(birthDay) );
			pstmt.setString(8, birthMonth );
			pstmt.setInt(9, Integer.parseInt(birthYear) );
			pstmt.executeUpdate();
			pstmt.close();
			
			pstmt = con.prepareStatement(query2);
			pstmt.setInt(1, Integer.parseInt(regnoVal) );
			pstmt.setString(2, gfnmVal);
			pstmt.setString(3, gmnmVal);
			pstmt.setString(4, glnmVal);
			pstmt.setLong(5, Long.parseLong(gphoneVal));
			pstmt.executeUpdate();
			pstmt.close();
			
			pstmt = con.prepareStatement(query3);
			pstmt.setInt(1, Integer.parseInt(regnoVal) );
			pstmt.setString(2, isAdminVal);
			pstmt.setString(3, passVal);
			pstmt.executeUpdate();
			pstmt.close();
			
			pstmt = con.prepareStatement(query4);
			pstmt.setInt(1, Integer.parseInt(regnoVal) );
			pstmt.setString(2, "present");
			pstmt.setString(3, pre_addr1);
			pstmt.setString(4, pre_addr2);
			pstmt.setString(5, pre_landmark);
			pstmt.setString(6, pre_city);
			pstmt.setString(7, pre_pincode);
			pstmt.executeUpdate();
			pstmt.close();
			
			pstmt = con.prepareStatement(query4);
			pstmt.setInt(1, Integer.parseInt(regnoVal) );
			pstmt.setString(2, "permanent");
			pstmt.setString(3, per_addr1);
			pstmt.setString(4, per_addr2);
			pstmt.setString(5, per_landmark);
			pstmt.setString(6, per_city);
			pstmt.setString(7, per_pincode);
			pstmt.executeUpdate();
			pstmt.close();
			
			pstmt = con.prepareStatement(query5);
			for(String course : courseVals) {
				pstmt.setInt(1, Integer.parseInt(regnoVal) );
				pstmt.setInt(2, Integer.parseInt(course) );
				pstmt.executeUpdate();
			}
			pstmt.close();

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
		
		out.print("</body>");
		out.print("</html>");

	}//End of doPost
}//End of Class
