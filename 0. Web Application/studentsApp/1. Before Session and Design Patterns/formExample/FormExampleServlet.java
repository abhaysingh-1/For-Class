package com.jspiders.studentsapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FormExampleServlet extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException 
	{
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		String name = req.getParameter("myName");
		String pass = req.getParameter("pass");
		String gender = req.getParameter("gender");
		String education = req.getParameter("education");
		String[] knows = req.getParameterValues("knows");
		String[] owns = req.getParameterValues("owns");
		String aboutme = req.getParameter("aboutme");
		
		out.println("<B>Your Name :</B> "+name);
		out.println("<BR>");
		out.println("<B>Your Password :</B> "+pass);
		out.println("<BR>");
		out.println("<B>Your Gender :</B> "+gender);
		out.println("<BR>");
		out.println("<B>Your Education :</B> "+education);
		out.println("<BR>");
		
		out.println("<B>You know : </B>");
		for(String know : knows)
		{
			out.println("<BR>");
			out.println(know);
		}
		
		out.println("<B>You Have :</B> ");
		for(String own : owns)
		{
			out.println("<BR>");
			out.println(own);
		}
		out.println("<BR>");
		out.println("<B>About Your Self : </B>"+aboutme);
		
	}
}
