package com.jspiders.studentsapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginPageServlet extends HttpServlet 
{
	RequestDispatcher dispatcher = null;
	
	@Override
	protected void doGet(HttpServletRequest req, 
						HttpServletResponse resp)
	throws ServletException, IOException 
	{
		//Set the Dummy Cookie
		Cookie dummyCookie = new Cookie("isCookieEnabled", "check");
		resp.addCookie(dummyCookie);
		
		dispatcher= req.getRequestDispatcher("Login.html");
		dispatcher.include(req, resp);
	}//End of doPost
}//End of Class





