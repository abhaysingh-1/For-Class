<%!
	private int age = 100;
	public String name = "Rajani Kanth";
	
	public int getAge(){
		return age;
	}
	
	public String getName(){
		return name;
	}
	
	public String getName(String givenNM){
		return "Given Name is - "+givenNM;
	}
	
	{
		System.out.println("My Block in JSP");
	}
%>

<html>
<body>
	<h1>JSP Tags Example ...</h1>
	<BR><BR>
	Age 1 :- <%= age %>
	<BR>
	Age 2 :- <%= getAge() %>
	<BR><BR>
	Name 1 :- <%= name %>
	<BR>
	Name 2 :- <%= getName() %>
	<BR>
	Name 3 :- <%= getName("Praveen") %>
	<BR>
	Name 4 :- <%= name + getName() + getName("Praveen") %>
	<BR><BR>
	
	<%
		int j = 200;
		for(int i=0; i<5; i++)
		{
	%>
			Name 1 :- <%= name %>
			<BR>
	<%		
		}
	%>
	
</body>
</html>












