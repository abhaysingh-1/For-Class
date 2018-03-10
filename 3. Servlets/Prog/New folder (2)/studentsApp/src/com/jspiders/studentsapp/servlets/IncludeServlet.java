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

public class IncludeServlet extends HttpServlet{

	RequestDispatcher dispatcher = null;
	
	@Override
	protected void doGet(HttpServletRequest req, 
						 HttpServletResponse resp) 
	throws ServletException, IOException {
		
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.print(" 111111111111111111 ");
		
		dispatcher=req.getRequestDispatcher("index.html");
		dispatcher.include(req, resp);
		
		out.print(" 222222222222222222 ");
		
		dispatcher=req.getRequestDispatcher("currentDate");
		dispatcher.include(req, resp);
		
		out.print(" 333333333333333333 ");
		
		dispatcher=req.getRequestDispatcher("currentDate?fname=XXX&lname=YYY");
		dispatcher.include(req, resp);
		
		out.print(" 444444444444444444 ");
		
	}//End of doGet
}//End of Class










