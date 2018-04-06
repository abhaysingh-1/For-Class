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

public class BodyPageServlet extends HttpServlet{

	RequestDispatcher dispatcher =null;
	
	@Override
	protected void doGet(HttpServletRequest req, 
						 HttpServletResponse resp) 
	throws ServletException, IOException {
		
		String bodyVal = req.getParameter("body");
		String url="";
		if(bodyVal.equals("createProfile")){
			url="CreateProfile.html";
			
		}else if(bodyVal.equals("search")){
			url="Search.html";
			
		}else if(bodyVal.equals("advanceSearch")){
			url="AdvanceSearch.html";
			
		}else if(bodyVal.equals("changePassword")){
			url="ChangePassword.html";
		}
		
		
		dispatcher=req.getRequestDispatcher("header");
		dispatcher.include(req, resp);

		dispatcher=req.getRequestDispatcher(url);
		dispatcher.include(req, resp);
		
		dispatcher=req.getRequestDispatcher("Footer.html");
		dispatcher.include(req, resp);
		
	}//End of doGet
}//End of Class










