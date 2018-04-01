package com.jspiders.studentsapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginErrorServlet extends HttpServlet
{
	RequestDispatcher dispatcher = null;
	
	@Override
	protected void doGet(HttpServletRequest req, 
						 HttpServletResponse resp)
	throws ServletException, IOException 
	{
		doPost(req,resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, 
						 HttpServletResponse resp)
	throws ServletException, IOException 
	{
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		String errMsg = (String)req.getAttribute("errInfo");
		
		out.println("<font color=\"red\">"); 
		out.println(errMsg);
		out.println("</font>"); 
		out.println("</BR>"); 
		dispatcher = req.getRequestDispatcher("Login.html");
		dispatcher.include(req, resp);
	}//End of doPost
}//End of Class
