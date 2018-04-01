package com.jspiders.studentsapp.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspiders.studentsapp.dao.StudentDAO;
import com.jspiders.studentsapp.dao.StudentsInfoBean;

public class LoginServlet extends HttpServlet 
{
	RequestDispatcher dispatcher = null;
	
	@Override
	protected void doPost(HttpServletRequest req, 
						HttpServletResponse resp)
	throws ServletException, IOException 
	{
		/*
		 * Check Cookies are enabled or not
		 * If Not Enabled then don't allow the 
		 * user to Login
		 */
		Cookie[] rcvdCookies = req.getCookies();
		if(rcvdCookies==null)
		{
			dispatcher = req.getRequestDispatcher("CookieNotEnabledError.html");
			dispatcher.forward(req, resp);
			return;
		}
		
		/*
		 * If Cookies are enabled in the User Browser
		 * then Allow him to access the web application
		 */
		//1. Get the Form Data
		String regNum = req.getParameter("regno");
		String password = req.getParameter("pass");
		
		//2. Authenticate the Credentials by 
		//   interacting with DB
		StudentDAO dao = new StudentDAO();
		StudentsInfoBean data = dao.authenticate(regNum, password);
		
		//3. Generate the Response
		if(data!=null)
		{
			//Valid Credentials
			/*
			 * 1. Create a Session for the 
			 *    First Time
			 */
			HttpSession session = req.getSession(true);
			//Time in Seconds
			session.setMaxInactiveInterval(7*24*60*60);
			
			System.out.println("Session ID : "+session.getId());
			
			Cookie sessionCookie = new Cookie("JSESSIONID", session.getId());
			sessionCookie.setMaxAge(7*24*60*60);
			resp.addCookie(sessionCookie);
			
			session.setAttribute("info", data);
			dispatcher = req.getRequestDispatcher("home");
			dispatcher.forward(req, resp);
			
		}else{
			//In-Valid Credentials
			String errMsg = "Invalid User Name / Password";
			req.setAttribute("errInfo", errMsg);
			dispatcher = req.getRequestDispatcher("loginErr");
			dispatcher.forward(req, resp);
		}
	}//End of doPost
}//End of Class
