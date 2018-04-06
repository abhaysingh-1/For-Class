package com.jspiders.studentsapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateCookieServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, 
						 HttpServletResponse resp) 
	throws ServletException, IOException {
	
		//Create a Cookie
		Cookie myNameCookie = new Cookie("myName", "Praveen");
		resp.addCookie(myNameCookie);
		
		//Create an Another Cookie
		Cookie myLocationCookie = new Cookie("myLocation", "Bangalore");
		//Time in Sec
		myLocationCookie.setMaxAge(7*24*60*60);
		resp.addCookie(myLocationCookie);
		
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.print(" Created the Cookie... ");
		
	}//End of doGet
}//End of Class












