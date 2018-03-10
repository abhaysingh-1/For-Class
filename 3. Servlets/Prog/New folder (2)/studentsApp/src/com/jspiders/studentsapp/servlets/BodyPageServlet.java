package com.jspiders.studentsapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BodyPageServlet extends HttpServlet{

	RequestDispatcher dispatcher =null;
	
	@Override
	protected void doGet(HttpServletRequest req, 
						 HttpServletResponse resp) 
	throws ServletException, IOException {
		
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		//II. Validate the Session
		HttpSession session = req.getSession(false);
		if(session==null){
			//Invalid Session; Generate Login Page
			out.print("<font color=\"red\">");
			out.println("In-Valid Session !!! Pls Login ...");
			out.print("</font>");
			
			dispatcher=req.getRequestDispatcher("Login.html");
			dispatcher.include(req, resp);
		}else{
			//Valid Session; Generate Proper Response
			String bodyVal = req.getParameter("body");
			String url="";
			if(bodyVal.equals("createProfile")){
				url="CreateProfile.html";
				
			}else if(bodyVal.equals("search")){
				url="Search.html";
				
			}else if(bodyVal.equals("changePassword")){
				url="ChangePassword.html";
			}
			
			dispatcher=req.getRequestDispatcher("headerPage");
			dispatcher.include(req, resp);

			dispatcher=req.getRequestDispatcher(url);
			dispatcher.include(req, resp);
			
			dispatcher=req.getRequestDispatcher("Footer.html");
			dispatcher.include(req, resp);
		}
		
	}//End of doGet
}//End of Class










