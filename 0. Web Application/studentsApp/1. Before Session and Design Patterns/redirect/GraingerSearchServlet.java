package com.jspiders.studentsapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GraingerSearchServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, 
						 HttpServletResponse resp) 
	throws ServletException, IOException {
	
		//Get the ServletContext Object
		String keywordVal = req.getParameter("keyword");
		String url = "https://www.grainger.com/search?searchQuery=";
		String redirectUrl = url + keywordVal;
		
		resp.sendRedirect(redirectUrl);

	}//End of doGet
}//End of Class












