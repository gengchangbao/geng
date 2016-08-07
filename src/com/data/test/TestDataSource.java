package com.data.test;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.data.dao.impl.PageDaoImpl;
import com.data.dao.impl.UserDaoImpl;

/*
 * ≤‚ ‘ ˝æ›
 */
public class TestDataSource {
	@Test
	public void getConnection() throws SQLException{
	ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
	DataSource ds =(DataSource) ac.getBean("dataSource");
	PageDaoImpl pageDao=(PageDaoImpl) ac.getBean("pageDao");
	System.out.println(pageDao);
	}
	public static void main(String[] args) {
		try {
			new TestDataSource().getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
