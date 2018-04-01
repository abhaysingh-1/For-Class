package com.jspiders.studentsapp.dao;

public class StudentDAOFactory 
{
	private StudentDAOFactory(){}
	
	private static String dbInteractionType = "jdbc";
	
	public static StudentDAO getDAOInstance()
	{
		StudentDAO ref = null;
		
		if(dbInteractionType.equals("jdbc")){
			ref = new StudentDAOJDBCImpl();
		}else if(dbInteractionType.equals("hibernate")){
			ref = new StudentDAOHibernateImpl();
		}else if(dbInteractionType.equals("spring-jdbc")){
			ref = new StudentDAOSpringJDBCImpl();
		}
		return ref;
	}
}//End of Class
