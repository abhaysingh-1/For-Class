package com.jspiders.studentsapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HeaderPageServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, 
						 HttpServletResponse resp) 
	throws ServletException, IOException {
		doGet(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, 
						 HttpServletResponse resp) 
	throws ServletException, IOException {
	
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();

		out.print("<html>");
		out.print("<head>");
		out.print("	<link rel=\"stylesheet\" href=\"./css/header.css\">");
		out.print("</head>");
		out.print("<body>");
		out.print("		<div style=\"margin:0px;padding-bottom:0px;\" id=\"div\">");
		out.print("			<ul>");
		out.print("				<li id=\"name\"><font class=\"color\">Welcome </font>FirstName MiddleName LastName</li>");
		out.print("				<li>Login @ <span class=\"highlight\">20-March-2018 6:26 AM</span></li>");
		out.print("				<li>Last Login @ <span class=\"highlight\">19-March-2018 5:00 PM</span>");
		out.print("				</li>");
		out.print("				<li><a href=\"#\" class=\"color\">Change Password</a></li>");
		out.print("				<li><a href=\"#\" class=\"color\">Logout</a></li>");
		out.print("			</ul>");
		out.print("		</div>");
		out.print("		<div class=\"header\" align=\"left\" id=\"top\">");
		out.print("			<ul class=\"topnav\">");
		out.print("				<li><a href=\"#\" class=\"a\" id=\"font\">Home</a></li>");
		out.print("				<li><a href=\"#\" class=\"a\" id=\"font\">Create Profile</a></li>");
		out.print("				<li class=\"dropdown\">");
		out.print("					<a href=\"#\" class=\"dropbtn\" id=\"font\">Search</a>");
		out.print("				<div class=\"dropdown-content\">");
		out.print("					<a href=\"#\">By Register</a>");
		out.print("      				<a href=\"#\">StudentInfo</a>");
		out.print("     				<a href=\"#\">By Site</a>");
		out.print("				</div>");
		out.print("				</li>");
		out.print("				<li class=\"dropdown\">");
		out.print("					<a href=\"#\" class=\"dropbtn\" id=\"font\">Admin Tasks</a>");
		out.print("				<div class=\"dropdown-content\">");
		out.print("					<a href=\"#\">Add New Course</a>");
		out.print("      				<a href=\"#\"Unlock Account</a>");
		out.print("     				<a href=\"#\">Add Mock Ratings</a>");
		out.print("				</div>");
		out.print("				</li>");
		out.print("				<li><a href=\"#\" class=\"a\" id=\"font\">Requirements</a></li>");
		out.print("				</ul>");
		out.print("</body>");
		out.print("</html>");
	}//End of doGet
}//End of Class












