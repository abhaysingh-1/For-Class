package com.jspiders.studentsapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class HeaderServlet extends HttpServlet{

	RequestDispatcher dispatcher =null;
	
	@Override
	protected void doGet(HttpServletRequest req, 
						 HttpServletResponse resp) 
	throws ServletException, IOException {
		
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		//Validate a Session
		HttpSession session = req.getSession(false);
		if(session==null){
			out.print("<font color=\"red\">");
			out.println("In-Valid Session !!! Pls Login ...");
			out.print("</font>");
			
			dispatcher=req.getRequestDispatcher("Login.html");
			dispatcher.include(req, resp);
		}else{
			String isadmin = (String)session.getAttribute("isAdmin");
			out.print("<html>");
			out.print("<body>");
			out.print("	<table width=\"100%\" border=\"1\">");
			out.print("		<tr>");
			out.print("			<td align=\"center\"><a href=\"#\">Home</a></td>");
			if(isadmin.equals("Y")){
				out.print("		<td align=\"center\"><a href=\"./bodyPage?body=createProfile\">Create Profile</a></td>");
			}
			out.print("			<td align=\"center\"><a href=\"./bodyPage?body=search\">Search</a></td>");
			out.print("			<td align=\"center\"><a href=\"./bodyPage?body=changePassword\">Change Password</a></td>");
			out.print("			<td align=\"center\"><a href=\"./logout\">Logout</a></td>");
			out.print("		</tr>");
			out.print("	</table>");
			out.print("</body>");
			out.print("</html>");
			out.print("<BR><BR>");
			
		}//End of Session Validation
	}//End of doGet
}//End of Class










