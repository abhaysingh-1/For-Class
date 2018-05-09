package com.jspiders.studentsapp.servlets;
 
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateProfileServlet extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, 
						  HttpServletResponse resp)
	throws ServletException, IOException 
	{
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
		
		//Student 10th Standard Info
		String colnm10 = req.getParameter("sslc.colnm");
		String uninm10 = req.getParameter("sslc.uninm");
		String branch10 = req.getParameter("sslc.branch");
		String yop10 = req.getParameter("sslc.yop");
		String percentage10 = req.getParameter("sslc.percentage");
		
		//Student 12th Standard Info
		String colnm12 = req.getParameter("puc.colnm");
		String uninm12 = req.getParameter("puc.uninm");
		String branch12 = req.getParameter("puc.branch");
		String yop12 = req.getParameter("puc.yop");
		String percentage12 = req.getParameter("puc.percentage");

		//Student Diploma Info
		String colnmUG = req.getParameter("ug.colnm");
		String uninmUG = req.getParameter("ug.uninm");
		String branchUG = req.getParameter("ug.branch");
		String yopUG = req.getParameter("ug.yop");
		String percentageUG = req.getParameter("ug.percentage");
		
		//Student Degree Info
		String colnmDEG = req.getParameter("deg.colnm");
		String uninmDEG = req.getParameter("deg.uninm");
		String branchDEG = req.getParameter("deg.branch");
		String yopDEG = req.getParameter("deg.yop");
		String percentageDEG = req.getParameter("deg.percentage");
		
		//Student Master Degree Info
		String colnmPG = req.getParameter("pg.colnm");
		String uninmPG = req.getParameter("pg.uninm");
		String branchPG = req.getParameter("pg.branch");
		String yopPG = req.getParameter("pg.yop");
		String percentagePG = req.getParameter("pg.percentage");
		
		//II. Store the Form Data into DB
		Connection con = null;
		PreparedStatement pstmt = null;
		StringBuffer htmlResp = new StringBuffer();
		
		htmlResp.append("<html>");
		htmlResp.append("<body>");
		
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
			
			String query6 = " insert into student_educationinfo "
							+ " values (?, ?, ?, ?, ?, ?, ?) ";

			pstmt = con.prepareStatement(query1);
			pstmt.setInt(1, Integer.parseInt(regnoVal) );
			pstmt.setString(2, fnmVal);
			pstmt.setString(3, mnmVal);
			pstmt.setString(4, lnmVal);
			pstmt.setString(5, emailVal);
			pstmt.setLong(6, Long.parseLong(phoneVal));

			String[] dobVals = dobVal.split("-");
			String birthDay = dobVals[2];
			String birthMonth = getMonthName(dobVals[1]);
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

			pstmt = con.prepareStatement(query6);
			
			//Insert Student SSLC Info - Its Mandatory for Student
			pstmt.setInt(1, Integer.parseInt(regnoVal) );
			pstmt.setInt(2, 1); //SSLC
			pstmt.setString(3, colnm10); 
			pstmt.setString(4, uninm10); 
			pstmt.setString(5, branch10); 
			pstmt.setString(6, yop10); 
			pstmt.setString(7, percentage10); 
			pstmt.executeUpdate();

			//Insert Student Degree Info - Its Mandatory for Student
			pstmt.setInt(1, Integer.parseInt(regnoVal) );
			pstmt.setInt(2, 4); //Degree
			pstmt.setString(3, colnmDEG); 
			pstmt.setString(4, uninmDEG); 
			pstmt.setString(5, branchDEG); 
			pstmt.setString(6, yopDEG); 
			pstmt.setString(7, percentageDEG); 
			pstmt.executeUpdate();
			
			//Insert Student PUC Info - If Exists
			if(colnm12 != null && !colnm12.equals("")) {
				pstmt.setInt(1, Integer.parseInt(regnoVal) );
				pstmt.setInt(2, 2); //PUC
				pstmt.setString(3, colnm12); 
				pstmt.setString(4, uninm12); 
				pstmt.setString(5, branch12); 
				pstmt.setString(6, yop12); 
				pstmt.setString(7, percentage12); 
				pstmt.executeUpdate();
			}
			
			//Insert Student Diploma Info - If Exists
			if(colnmUG != null && !colnmUG.equals("")) {
				pstmt.setInt(1, Integer.parseInt(regnoVal) );
				pstmt.setInt(2, 3); //Diploma
				pstmt.setString(3, colnmUG); 
				pstmt.setString(4, uninmUG); 
				pstmt.setString(5, branchUG); 
				pstmt.setString(6, yopUG); 
				pstmt.setString(7, percentageUG); 
				pstmt.executeUpdate();
			}
			
			//Insert Student Master Degree Info - If Exists
			if(colnmPG != null && !colnmPG.equals("")) {
				pstmt.setInt(1, Integer.parseInt(regnoVal) );
				pstmt.setInt(2, 5); //Master Degree
				pstmt.setString(3, colnmPG); 
				pstmt.setString(4, uninmPG); 
				pstmt.setString(5, branchPG); 
				pstmt.setString(6, yopPG); 
				pstmt.setString(7, percentagePG); 
				pstmt.executeUpdate();
			}
			
			pstmt.close();

			//4. Process the Results returned by SQL Queries
			//Generate HTML Response to Print above Data 
			htmlResp.append("<font color=\"green\">"); 
			htmlResp.append("Successfully Created the Profile ...");
			htmlResp.append("</font>");
			
		} catch (Exception e) {
			htmlResp.append("<font color=\"red\">"); 
			htmlResp.append("Unable to Create the Profile !!!");
			htmlResp.append("</font>");
			
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
		
		htmlResp.append("</body>");
		htmlResp.append("</html>");
		
		//Print the Response in Browser
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.print(htmlResp);

	}//End of doPost
	
	
	private String getMonthName(String monthVal){
		switch(monthVal){
			case "1": return "JAN";
			case "2": return "FEB";
			case "3": return "MAR";
			case "4": return "APR";
			case "5": return "MAY";
			case "6": return "JUN";
			case "7": return "JUL";
			case "8": return "AUG";
			case "9": return "SEP";
			case "10": return "OCT";
			case "11": return "NOV";
			default : return "DEC";
		}
	}//End of getMonthName
	
}//End of Class
