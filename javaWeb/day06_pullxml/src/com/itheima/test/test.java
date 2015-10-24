package com.itheima.test;

import java.util.List;

import org.junit.Test;

import com.itheima.dao.UserGet;
import com.itheima.dao.pullxml;
import com.itheima.domain.Student;

public class test {
	
	@Test
	public void tListToXml() throws Exception
	{
		List<Student>stulist=UserGet.getUser();
		pullxml.listToxml(stulist);
	}
	@Test
	public void tXmlToList() throws Exception
	{
		List<Student> stulist=pullxml.listToXml();
		for(Student s:stulist)
		{
			System.out.println(s.toString());
		}
	}

}
