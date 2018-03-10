package com.jspiders.studentsapp.servlets;
 
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class MyAnotherServlet implements Servlet{

	public MyAnotherServlet() {
		System.out.println("Inside MyAnotherServlet Constructor");
	}
	public void destroy() {
		System.out.println("Inside MyAnotherServlet destroy() Method");
	}

	public ServletConfig getServletConfig() {
		return null;
	}

	public String getServletInfo() {
		return null;
	}

	public void init(ServletConfig arg0) 
	throws ServletException {
		System.out.println("Inside MyAnotherServlet init() Method");
	}

	public void service(ServletRequest req, 
			 			ServletResponse resp) 
	throws ServletException, IOException {
		System.out.println("Inside MyAnotherServlet service() Method");
		
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.print("Inside My Another Servlet ....");
	}

}
