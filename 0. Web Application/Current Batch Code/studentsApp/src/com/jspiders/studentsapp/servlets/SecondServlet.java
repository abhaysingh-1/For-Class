package com.jspiders.studentsapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SecondServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, 
						 HttpServletResponse resp) 
	throws ServletException, IOException {
	
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		//Get the ServletContext Object
		ServletContext ctx = getServletContext();
		Object obj = ctx.getAttribute("ctxKey");
		
		if(obj==null){
			out.print(" Context Attribute is NOT Present!!! ");
		}else{
			out.print(" Context Attribute is Present... ");
			out.print(" Value is : "+obj.toString());
		}
		
		Object obj2 = req.getAttribute("reqKey");
		
		if(obj2==null){
			out.print(" Request Attribute is NOT Present!!! ");
		}else{
			out.print(" Request Attribute is Present... ");
			String str = (String)obj2;
			out.print(" Value is : "+str);
		}
		
		
		
		
		
		
		
		
		
	}//End of doGet
}//End of Class












