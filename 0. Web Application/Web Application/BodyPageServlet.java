package com.jspiders.studentsapp.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BodyPageServlet extends HttpServlet 
{
	RequestDispatcher dispatcher = null;
	
	@Override
	protected void doGet(HttpServletRequest req, 
						 HttpServletResponse resp)
	throws ServletException, IOException 
	{
		String bodyPage = req.getParameter("body");
		String url = null;
		if(bodyPage.equals("createProfile")){
			url = "CreateProfile.html";
		}else if(bodyPage.equals("search")){
			url = "Search.html";
		}else if(bodyPage.equals("changePassword")){
			url = "ChangePassword.html";
		}
		
		dispatcher = req.getRequestDispatcher("Header.html");
		dispatcher.include(req, resp);
		
		dispatcher = req.getRequestDispatcher(url);
		dispatcher.include(req, resp);
		
		dispatcher = req.getRequestDispatcher("Footer.html");
		dispatcher.include(req, resp);
		
	}//End of doPost
}//End of Class





