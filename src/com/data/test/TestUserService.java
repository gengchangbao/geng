package com.data.test;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.data.model.User;
import com.data.service.UserService;

/*
 * ≤‚ ‘UserService
 */
public class TestUserService {
	private static UserService us;
	
	@BeforeClass
	public static void insertUserService(){
		
		ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
				us=(UserService) ac.getBean("userService");
	}
	
	/**
	 * ≤Â»Î”√ªß
	 * @throws SQLException
	 */
	@Test
	public void insertUser() throws SQLException{
		try {
		User u=new User();
		u.setEmail("¿≈Á∞Ò");
		u.setPassword("23456111");
		us.saveEntity(u);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		insertUserService();
		try {
			new TestUserService().insertUser();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
 