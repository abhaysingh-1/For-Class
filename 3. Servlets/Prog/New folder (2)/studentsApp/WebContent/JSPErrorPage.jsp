<%@page isErrorPage="true"%>

<html>
<body>
	Exception Occurred !!! Exception is :
	<font color="red">
		<%= exception.getMessage() %>
	</font>
</body>
</html>	