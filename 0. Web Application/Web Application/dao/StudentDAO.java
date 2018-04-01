package com.jspiders.studentsapp.dao;

import java.util.List;

public interface StudentDAO 
{
	StudentInfoBean authenticate(String regnoVal, String passVal);
	List<StudentInfoBean> getAllStudents();
}
