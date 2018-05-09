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
		//Get the Form Data
		String name = req.getParameter("myName");
		String pass = req.getParameter("pass");
		String gender = req.getParameter("gender");
		String education = req.getParameter("education");
		String[] knows = req.getParameterValues("knows");
		String[] owns = req.getParameterValues("owns");
		String aboutme = req.getParameter("aboutme");
		
		//Generate HTML Response to Print above Data 
		StringBuffer htmlResp = new StringBuffer("");
		
		htmlResp.append("<B>Your Name :</B> "+name);
		htmlResp.append("<BR><BR>");
		htmlResp.append("<B>Your Password :</B> "+pass);
		htmlResp.append("<BR><BR>");
		htmlResp.append("<B>Your Gender :</B> "+gender);
		htmlResp.append("<BR><BR>");
		htmlResp.append("<B>Your Education :</B> "+education);
		htmlResp.append("<BR><BR>");
		
		htmlResp.append("<B>You know : </B>");
		
		for(String know : knows){
			htmlResp.append("<BR>");
			htmlResp.append(know);
		}
		
		htmlResp.append("<BR><BR>");
		
		htmlResp.append("<B>You Have :</B> ");
		
		for(String own : owns) {
			htmlResp.append("<BR>");
			htmlResp.append(own);
		}
		htmlResp.append("<BR><BR>");
		htmlResp.append("<B>About Your Self : </B>"+aboutme);
		
		//Print the Above Response in Browser
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.print(htmlResp);
	}
}
