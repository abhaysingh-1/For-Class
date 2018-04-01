package com.jspiders.studentsapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspiders.studentsapp.dao.StudentDAO;
import com.jspiders.studentsapp.dao.StudentsInfoBean;

public class AllStudentsControllerServlet extends HttpServlet 
{
	RequestDispatcher dispatcher = null;
	
	@Override
	protected void doGet(HttpServletRequest req, 
						HttpServletResponse resp)
	throws ServletException, IOException 
	{
		//Validate the Session
		HttpSession session = req.getSession(false);
		if(session==null)
		{
			String errMsg = "Invalid Session !!! Pls Login ...";
			req.setAttribute("errInfo", errMsg);
			dispatcher = req.getRequestDispatcher("loginErr");
			dispatcher.forward(req, resp);
			
		}else{
			StudentDAO dao = new StudentDAO();
			List<StudentsInfoBean> dataList = dao.getAllStudents();
			req.setAttribute("data", dataList);
			dispatcher = req.getRequestDispatcher("allStudentsView");
			dispatcher.forward(req, resp);
		}
		
	}//End of doGet
}//End of Class





