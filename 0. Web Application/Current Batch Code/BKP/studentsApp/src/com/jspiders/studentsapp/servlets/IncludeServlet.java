package com.jspiders.studentsapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
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
		
		out.print(" 11111111111111111111 ");
		
		dispatcher = req.getRequestDispatcher("index.html");
		dispatcher.include(req, resp);
		
		out.print(" 22222222222222222222 ");
		
		dispatcher = req.getRequestDispatcher("login");
		dispatcher.include(req, resp);
		
		out.print(" 33333333333333333333 ");
		
		dispatcher = req.getRequestDispatcher("currentDate?fname=XXX&lname=YYY");
		dispatcher.include(req, resp);
		
		out.print(" 44444444444444444444 ");
		
	}//End of doGet
}//End of Class
















