package http;

public class MySubClass extends MySupuerClass 
{
	public void mymethod(Object obj1, Object obj2) 
	{
		//Some Business Logic
		System.out.println("AAAAAAAAAAA");
	}

	public void mymethod(String str1, String str2) 
	{
		//Some Other Business Logic
		System.out.println("BBBBBBBBBBBB");
	}
	
	public void myAnotherMethod(String str1, String str2) 
	{
		//Another Business Logic
		System.out.println("CCCCCCCCCCC");
	}
}//End of Class
