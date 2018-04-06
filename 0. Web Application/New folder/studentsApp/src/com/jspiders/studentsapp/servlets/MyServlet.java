package com.jspiders.studentsapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class MyServlet implements Servlet{

	public MyServlet() {
		System.out.println("Inside Constructor");
	}
	
	public void init(ServletConfig config) 
	throws ServletException {
		System.out.println("Inside init() method");
	}
	
	public void service(ServletRequest req, 
						ServletResponse resp) 
	throws ServletException, IOException {
		System.out.println("Inside service() Method");
		 
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.print("Inside My Servlet ....");
	}
	
	public void destroy() {
		System.out.print("Inside destroy() Method");

	}

	public ServletConfig getServletConfig() {
		return null;
	}

	public String getServletInfo() {
		return null;
	}

}//End of Class
