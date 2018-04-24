package com.jspiders.studentsapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Driver;

public class LoginServlet extends HttpServlet 
{
	RequestDispatcher dispatcher = null;
	
	@Override
	protected void doPost(HttpServletRequest req, 
						 HttpServletResponse resp)
	throws ServletException, IOException 
	{
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		//I. Get the Form Data
		String regnoVal = req.getParameter("regno");
		String passVal = req.getParameter("pass");
		
	   /* II. Authenticate the Credentials by 
		*      interacting with DB & Generate 
		*      the Response
		*/
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try 
		{
			//1. Load the Driver
			//Driver Class : com.mysql.jdbc.Driver
			Driver driverRef = new Driver();
			DriverManager.registerDriver(driverRef);
			
			//2. Get the DB Connection via Driver 
			String dbUrl = "jdbc:mysql://localhost:3306/studentsapp_db?user=j2ee&password=j2ee";
			con = DriverManager.getConnection(dbUrl);
			
			//3. Issue SQL Queries via Connection 
			String query = " select * from "+
							" student_info si, "+
							" guardian_info gi, "+
							" student_otherinfo soi "+
							" where si.regno = gi.regno "+
							" and si.regno = soi.regno "+
							" and soi.regno = ? "+
							" and soi.password = ? "+
							" and soi.login_attempts <= 3";
			
			System.out.println("Query : "+query);
			
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(regnoVal) );
			pstmt.setString(2, passVal);
			rs = pstmt.executeQuery();
			
			//4. Process the Results returned by SQL Queries
			if(rs.next())
			{
				//Valid credentials
				
				//1. Create a Session
				HttpSession session = req.getSession(true);
				//Store Reg No & Login Time in Session Attribute
				session.setAttribute("userName", regnoVal);
				session.setAttribute("loginTime", new Date().toString());
				
				//Time in Seconds
				//session.setMaxInactiveInterval(1*60);
				
				System.out.println("Session ID ---> "+session.getId());
				
				int regnum = rs.getInt("regno");
				String fNM = rs.getString("firstname");
				String mNM = rs.getString("middlename");
				String lNM = rs.getString("lastname");
				
				//Store Complete User Name in Session Attribute
				String fullNm = 
						 ( (fNM==null || fNM.trim().isEmpty()) ? "" : fNM.trim() )
						+ " "
						+( (mNM==null || mNM.trim().isEmpty() || mNM.equals("NA")) ? "" : mNM.trim() )
						+ " "
						+( (lNM==null || lNM.trim().isEmpty()|| lNM.equals("NA")) ? "" : lNM.trim() );
				
				session.setAttribute("userFullName", fullNm);
				
				//Store Last Login Time in Session Attribute
				String lastLogin = rs.getString("last_login_time");
				String lastLoginTime = (lastLogin==null || lastLogin.trim().isEmpty()) ? "This is Your First Login !!!" : lastLogin.trim();
				session.setAttribute("lastLoginTm", lastLoginTime);
				
				String gfNM = rs.getString("gi.gfirstname");
				String gmNM = rs.getString("gi.gmiddlename");
				String glNM = rs.getString("gi.glastname");
				String isAdmin = rs.getString("soi.isadmin");
				
				dispatcher = req.getRequestDispatcher("header");
				dispatcher.include(req, resp);
				
				if(isAdmin.equals("Y"))
				{
					String url = "./allStudentsView";
					String encodedUrl = resp.encodeURL(url);
					out.println("<a href=\""+encodedUrl+"\"> Click Here <a> to View ALL Students Info ...");
					out.println("<BR> ");
				}
				out.println("<html> ");
				out.println("<body> ");
				out.println("<table>");
				out.println("<tr bgcolor=\"green\">");
				out.println("<td>Reg. No.</td>     ");
				out.println("<td>First Name</td>   ");
				out.println("<td>Middle Name</td>  ");
				out.println("<td>Last Name</td>    ");
				out.println("<td>G First Name</td> ");
				out.println("<td>G Middle Name</td>");
				out.println("<td>G Last Name</td>  ");
				out.println("</tr>");
				out.println("<tr> ");
				out.println("<td>"+regnum+"</td>  ");
				out.println("<td>"+fNM+"</td>");
				out.println("<td>"+mNM+"</td> ");
				out.println("<td>"+lNM+"</td>");
				out.println("<td>"+gfNM+"</td>");
				out.println("<td>"+gmNM+"</td> ");
				out.println("<td>"+glNM+"</td>");
				out.println("</tr>       ");
				out.println("</table>    ");
				out.println("</body>     ");
				out.println("</html>     ");
				
				dispatcher = req.getRequestDispatcher("Footer.html");
				dispatcher.include(req, resp);
				
				//Update Student_otherinfo table with Login Time
				String query2 = " update student_otherinfo "
								+ " set last_login_time = ?, "
								+ "     login_attempts = 0 "
								+ " where regno=? ";
				pstmt.close();
				pstmt = con.prepareStatement(query2);
				pstmt.setString(1, new Date().toString());
				pstmt.setInt(2, Integer.parseInt(regnoVal) );
				pstmt.executeUpdate();
				
			}else{
				//In-Valid credentials
				//Update Student_otherinfo table with Login Time
				String query2 = " update student_otherinfo "
								+ " set login_attempts = (login_attempts+1) "
								+ " where regno=? ";
				pstmt.close();
				pstmt = con.prepareStatement(query2);
				pstmt.setInt(1, Integer.parseInt(regnoVal) );
				pstmt.executeUpdate();
				
				out.print("<font color=\"red\">");
				out.println("In-Valid User Name / Password");
				out.println("<BR> Or your account may be Locked. Pls talk to Admin ");
				out.print("</font>");
				out.print("<BR><BR>");
				dispatcher = req.getRequestDispatcher("Login.html");
				dispatcher.include(req, resp);
					
			}
			
		} catch (Exception e) {
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
				if(rs!=null){
					rs.close();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}//End of outer try-catch block
		
	}//End of doPost
	
}//End of Class





