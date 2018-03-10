package com.jspiders.studentsapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
public class MyFirstServlet extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, 
						 HttpServletResponse resp) 
	throws ServletException, IOException {
		
		//Java Code to Generate Current Date & Time
		Date dateRef = new Date();
		String currentDate = dateRef.toString();
		
		//Get the Query String info
		String fNM = req.getParameter("fname");
		String lNM = req.getParameter("lname");
		
		//Send this HTML Response to Browser
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.print("<html>");
		out.print("<body>");
		out.print("<h1>");
		out.print("Current Date & Time is : ");
		out.print("<BR>");
		out.print("<font color=\"blue\">");
		out.print(currentDate);
		out.print("</font>");
		out.print("<BR><BR>");
		out.print("First Name : ");
		out.print(fNM);
		out.print("<BR>");
		out.print("Last Name : ");
		out.print(lNM);
		out.print("</h1>");
		out.print("<BR><BR>");
		
		for(int i=0; i<5; i++){
			out.print("First Name : ");
			out.print(fNM);
			out.print("<BR>");
		}
		
		out.print("</body>");
		out.print("</html>");
	}//End of doGet
	
}//End of Class









