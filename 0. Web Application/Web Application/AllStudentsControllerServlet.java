package com.jspiders.studentsapp.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspiders.studentsapp.dao.StudentDAO;
import com.jspiders.studentsapp.dao.StudentDAOFactory;
import com.jspiders.studentsapp.dao.StudentInfoBean;

public class AllStudentsControllerServlet extends HttpServlet 
{
	RequestDispatcher dispatcher = null;
	
	@Override
	protected void doGet(HttpServletRequest req, 
						HttpServletResponse resp)
	throws ServletException, IOException 
	{
		StudentDAO dao = StudentDAOFactory.getDAOInstance();
		List<StudentInfoBean> dataList = dao.getAllStudents();
		req.setAttribute("data", dataList);
		dispatcher = req.getRequestDispatcher("allStudentsView");
		dispatcher.forward(req, resp);
	}//End of doGet
}//End of Class





