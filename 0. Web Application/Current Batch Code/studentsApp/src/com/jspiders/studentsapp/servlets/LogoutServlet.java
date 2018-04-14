package com.jspiders.studentsapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Driver;

public class LogoutServlet extends HttpServlet{

	RequestDispatcher dispatcher = null;
	
	@Override
	protected void doGet(HttpServletRequest req, 
						 HttpServletResponse resp) 
	throws ServletException, IOException {
		
		//3. In-Validate Session ID
		HttpSession session = req.getSession(false);
		if(session!=null){
			session.invalidate();
		}
		
		//Generate Login Page
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		out.print("<font color=\"green\">");
		out.println("Thanks for Visisting ...");
		out.print("</font>");
		out.print("<BR><BR>");
		dispatcher = req.getRequestDispatcher("Login.html");
		dispatcher.include(req, resp);
		
	}//End of doGet
	
}//End of Class








