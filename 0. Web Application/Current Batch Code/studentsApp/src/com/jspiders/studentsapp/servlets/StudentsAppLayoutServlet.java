package com.jspiders.studentsapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StudentsAppLayoutServlet extends HttpServlet{

	RequestDispatcher dispatcher = null;
	
	@Override
	protected void doGet(HttpServletRequest req, 
						 HttpServletResponse resp) 
	throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		String header = req.getParameter("header");
		String body = req.getParameter("body");
		String footer = req.getParameter("footer");
		
		header 	= (header==null)? "header"		: header;
		body 	= (body==null)	? "Login.html" 	: body;
		footer 	= (footer==null)? "Footer.html" : footer;
		
		out.print("<html>");
		out.print("<body>");
		out.print("<table width=\"100%\" height=\"100%\">");
		
		// Header - Start
		out.print("<tr>");
		out.print("<td>");
		
		out.print("<table width=\"100%\" height=\"8%\">");
		out.print("<tr>");
		out.print("<td>");
		if(header.equals("login")) {
			out.print("&nbsp;");
		}else {
			dispatcher = req.getRequestDispatcher(header);
			dispatcher.include(req, resp);
		}
		out.print("</td>");
		out.print("</tr>");
		out.print("</table>");
		
		out.print("</td>");
		out.print("</tr>");
		// Header - End
		
		
		// Body - Start
		out.print("<tr>");
		out.print("<td>");
		
		out.print("<table width=\"100%\" height=\"87%\" >");
		out.print("<tr>");
		out.print("<td>");
		dispatcher = req.getRequestDispatcher(body);
		dispatcher.include(req, resp);
		out.print("</td>");
		out.print("</tr>");
		out.print("</table>");
		
		out.print("</td>");
		out.print("</tr>");
		// Body - End

		
		// Footer - Start
		out.print("<tr>");
		out.print("<td>");
		
		out.print("<table width=\"100%\" height=\"5%\">");
		out.print("<tr>");
		out.print("<td>");
		dispatcher = req.getRequestDispatcher(footer);
		dispatcher.include(req, resp);
		out.print("</td>");
		out.print("</tr>");
		out.print("</table>");
		
		out.print("</td>");
		out.print("</tr>");
		// Footer - End
		
		out.print("</table>");
		out.print("</body>");
		out.print("</html>");

	}//End of doGet()
	
	@Override
	protected void doPost(HttpServletRequest req, 
						  HttpServletResponse resp) 
	throws ServletException, IOException {
		doGet(req, resp);
	}//End of doGet()
}//End of Class
