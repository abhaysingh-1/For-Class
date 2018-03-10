<%@page import="java.util.Date"%>
 
<%!
	public void jspInit(){
		System.out.println("Inside jspInit() Method ...");
	}

	public void jspDestroy(){
		System.out.println("Inside jspDestroy() Method ...");
	}
%>

<%
	System.out.println("Inside _jspService() Method ...");
	
	//Java Code to Generate Current Date & Time
	Date dateRef = new Date();
	String currentDate = dateRef.toString();
	
	//Get the Query String info
	String fnameVal = request.getParameter("fname");
	String lnameVal = request.getParameter("lname");
%>

<html>
<body>
	<h1>
		<!-- HTML Comment -->
		<%-- JSP Comment --%>
		Current Date & Time is :
		<BR>
		<font color="green"> 
			<%= currentDate %>
		</font>
		<BR><BR>
		First Name : <%= fnameVal %>
		<BR>
		Last Name : <%= lnameVal %>
	</h1>
</body>
</html>





