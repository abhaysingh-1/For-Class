<html>
<body>
	Creating an Object in JSP ...
	<jsp:useBean 
		id="myBean" 
		class="com.jspiders.studentsapp.dao.StudentInfoBean" 
		scope="request" />
	
	Reg. No. value is : 
	<jsp:getProperty name="myBean" property="regno" />
	
	Changing the Reg. No. Value ...
	<jsp:setProperty name="myBean" property="regno" value="10"/>
	
	New Reg. No. value is : 
	<jsp:getProperty name="myBean" property="regno" />
</body>
</html>


