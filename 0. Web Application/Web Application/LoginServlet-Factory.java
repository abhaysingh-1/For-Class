package com.jspiders.studentsapp.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspiders.studentsapp.dao.StudentDAO;
import com.jspiders.studentsapp.dao.StudentDAOFactory;
import com.jspiders.studentsapp.dao.StudentInfoBean;

public class LoginServlet extends HttpServlet 
{
	RequestDispatcher dispatcher = null;
	
	@Override
	protected void doPost(HttpServletRequest req, 
						 HttpServletResponse resp)
	throws ServletException, IOException 
	{
		//1. Get the Form Data
		String regnoVal = req.getParameter("regno");
		String passVal = req.getParameter("pass");
		
		//2. Interact with DB
		StudentDAO dao = StudentDAOFactory.getDAOInstance();
		StudentInfoBean data = dao.authenticate(regnoVal, passVal);
		
		//3. Generate Response
		String url="";
		if(data!=null)
		{
			//Valid credentials
			req.setAttribute("studentData", data);
			url = "home";
			
		}else{
			//In-Valid credentials
			req.setAttribute("errInfo", "In-Valid User Name / Password");
			url = "loginErr";
			
		}//End of if-else
		
		dispatcher = req.getRequestDispatcher(url);
		dispatcher.forward(req, resp);
		
	}//End of doPost
}//End of Class





