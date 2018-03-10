package com.jspiders.studentsapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutServlet extends HttpServlet{

	RequestDispatcher dispatcher =null;
	
	@Override
	protected void doGet(HttpServletRequest req, 
						 HttpServletResponse resp) 
	throws ServletException, IOException {
		
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		//III. In-Validate the Session
		HttpSession session = req.getSession(false);
		if(session!=null){
			session.invalidate();
		}
		//Generate Login Page
		out.print("<font color=\"green\">");
		out.println("Thanks for Visiting ...");
		out.print("</font>");
		
		dispatcher=req.getRequestDispatcher("Login.html");
		dispatcher.include(req, resp);
		
	}//End of doGet
}//End of Class










