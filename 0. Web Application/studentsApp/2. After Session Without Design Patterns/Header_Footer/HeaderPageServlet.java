package com.jspiders.studentsapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class HeaderPageServlet extends HttpServlet{

	RequestDispatcher dispatcher = null;
	
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

		HttpSession session = req.getSession(false);
		if(session==null){
			//In-Valid Session
			out.print("<font color=\"red\">");
			out.println("In-Valid Session !!! Pls Login ...");
			out.print("</font>");
			out.print("<BR><BR>");
			dispatcher = req.getRequestDispatcher("Login.html");
			dispatcher.include(req, resp);
		}else{
			//Get the required info from Session Attriburtes
			String userFullName = (String)session.getAttribute("userFullName");
			String loginTm = (String)session.getAttribute("loginTime");
			String lastloginTm = (String)session.getAttribute("lastLoginTm");
			
			out.print("<html>");
			out.print("<head>");
			out.print("	<link rel=\"stylesheet\" href=\"./css/header.css\">");
			out.print("</head>");
			out.print("<body>");
			out.print("		<div style=\"margin:0px;padding-bottom:0px;\" id=\"div\">");
			out.print("			<ul>");
			out.print("				<li id=\"name\"><font class=\"color\">Welcome </font>"+userFullName+"</li>");
			out.print("				<li>Login @ <span class=\"highlight\">"+loginTm+"</span></li>");
			out.print("				<li>Last Login @ <span class=\"highlight\">"+lastloginTm+"</span>");
			out.print("				</li>");
			out.print("				<li><a href=\"./bodyPage?body=changePassword\" class=\"color\">Change Password</a></li>");
			out.print("				<li><a href=\"./logout\" class=\"color\">Logout</a></li>");
			out.print("			</ul>");
			out.print("		</div>");
			out.print("		<div class=\"header\" align=\"left\" id=\"top\">");
			out.print("			<ul class=\"topnav\">");
			out.print("				<li><a href=\"#\" class=\"a\" id=\"font\">Home</a></li>");
			//out.print("				<li><a href=\"./bodyPage?body=createProfile\" class=\"a\" id=\"font\">Create Profile</a></li>");
			out.print("				<li class=\"dropdown\">");
			out.print("					<a href=\"#\" class=\"dropbtn\" id=\"font\">Search</a>");
			out.print("				<div class=\"dropdown-content\">");
			out.print("					<a href=\"./bodyPage?body=search\">By Register Number</a>");
			out.print("      				<a href=\"./bodyPage?body=searchByStudentInfo\">By Student Info</a>");
			out.print("     				<a href=\"./bodyPage?body=advanceSearch\">By Site</a>");
			out.print("				</div>");
			out.print("				</li>");
			out.print("				<li class=\"dropdown\">");
			out.print("					<a href=\"#\" class=\"dropbtn\" id=\"font\">Admin Tasks</a>");
			out.print("				<div class=\"dropdown-content\">");
			out.print("					<a href=\"./bodyPage?body=createProfile\">Create Student Profile</a>");
			out.print("					<a href=\"#\">Add New Course</a>");
			out.print("      			<a href=\"#\">Unlock Account</a>");
			out.print("     			<a href=\"#\">Add Mock Ratings</a>");
			out.print("     			<a href=\"#\">Add Requirement</a>");
			out.print("				</div>");
			out.print("				</li>");
			out.print("				<li><a href=\"#\" class=\"a\" id=\"font\">Requirements</a></li>");
			out.print("				</ul>");
			out.print("</body>");
			out.print("</html>");
		}
		
	}//End of doGet
}//End of Class












