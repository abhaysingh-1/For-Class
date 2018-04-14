package com.jspiders.studentsapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FormExampleServelt extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, 
						 HttpServletResponse resp) 
	throws ServletException, IOException {
		
		//Extracts the Form Data
		String myNameVal = req.getParameter("myName");
		String passVal = req.getParameter("pass");
		String genderVal = req.getParameter("gender");
		String educationVal = req.getParameter("education");
		
		String[] knowsVals = req.getParameterValues("knows");
		String[] ownsVals = req.getParameterValues("owns");
		
		String aboutmeVal = req.getParameter("aboutme");
		String rememberVal = req.getParameter("remember");

		//Construts the Message using Form Data
		StringBuffer msg = new StringBuffer("");
		msg.append("Your Name is : ");
		msg.append(myNameVal);
		msg.append("Your Password : ");
		msg.append(passVal);
		msg.append("Your Gender : ");
		msg.append(genderVal);
		msg.append("Your Education : ");
		msg.append(educationVal);
		
		msg.append("You Know : ");
		for(String know : knowsVals){
			msg.append(know);
		}
		
		msg.append("You Own : ");
		for(String own : ownsVals){
			msg.append(own);
		}
		
		msg.append("About Yourself : ");
		msg.append(aboutmeVal);
		
		msg.append("Remember Me? : ");
		msg.append(rememberVal);
				
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.print(msg.toString());
		
	}//End of doPost

}//End of Class








