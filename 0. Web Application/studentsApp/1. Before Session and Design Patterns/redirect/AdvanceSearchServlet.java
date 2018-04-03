package com.jspiders.studentsapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdvanceSearchServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, 
						 HttpServletResponse resp) 
	throws ServletException, IOException {
	
		//Get the ServletContext Object
		String keywordVal = req.getParameter("keyword");
		String searchSiteVal = req.getParameter("searchSite");
		
		String url="";
		String redirectUrl;
		
		if(searchSiteVal.equals("google")){
			url = "https://www.google.co.in/search?q=";
			
			
		}else if(searchSiteVal.equals("")){
			url = "https://www.bing.com/search?q=";
			
		}else if(searchSiteVal.equals("")){
			url = "https://in.search.yahoo.com/search?p=";
		}
		
		redirectUrl = url + keywordVal;
		resp.sendRedirect(redirectUrl);

	}//End of doGet
}//End of Class












