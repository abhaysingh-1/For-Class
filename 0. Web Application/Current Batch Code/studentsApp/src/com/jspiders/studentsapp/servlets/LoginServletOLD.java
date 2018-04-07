package com.jspiders.studentsapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServletOLD extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, 
						 HttpServletResponse resp) 
	throws ServletException, IOException {
		
		String msg = "Inside doGet() method"; 
		
		//Get the Form Data
		String regnoVal = req.getParameter("regno");
		String passVal = req.getParameter("pass");
		
		msg = msg 
			  + " Reg. No. : "
			  + regnoVal
			  + " Password : "
			  + passVal;
		
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.print(msg);
		
	}//End of doGet
	
	@Override
	protected void doPost(HttpServletRequest req, 
						 HttpServletResponse resp) 
	throws ServletException, IOException {
		
		String msg = "Inside doPost() method"; 
		
		//Get the Form Data
		String regnoVal = req.getParameter("regno");
		String passVal = req.getParameter("pass");
		
		msg = msg 
			  + " Reg. No. : "
			  + regnoVal
			  + " Password : "
			  + passVal;
				
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.print(msg);
		
	}//End of doPost

}//End of Class








