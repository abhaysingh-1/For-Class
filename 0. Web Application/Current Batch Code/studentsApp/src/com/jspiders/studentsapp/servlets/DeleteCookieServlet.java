package com.jspiders.studentsapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteCookieServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, 
						 HttpServletResponse resp) 
	throws ServletException, IOException {
	
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		//Get the Cookies from request
		Cookie[] receivedCookies = req.getCookies();
		
		if(receivedCookies==null){
			out.print(" Cookies are NOT Present !!! ");
		}else{
			out.print(" Cookies are Present ... ");
			
			for(Cookie cookie : receivedCookies){
				String name = cookie.getName();
				if(name.equals("myLocation")){
					cookie.setMaxAge(0);
					resp.addCookie(cookie);
					out.print(" Deleted 'myLoacation' Cookie ");
					break;
				}//End of if
			}//End of for
		}//End of if-else
		
	}//End of doGet
}//End of Class












