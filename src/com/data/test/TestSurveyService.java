package com.data.test;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.data.model.User;
import com.data.service.SurveyService;
/*
 * ����SurveyService
 */
public class TestSurveyService {
	private static SurveyService ss;
	
	@BeforeClass
	public static void insertUserService(){
		
		ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
				ss=(SurveyService) ac.getBean("surveyService");
	}
	
	/**
	 *  �½�����
	 */
	@Test
	public void newSurvey() throws SQLException{
		User user=new User();
		user.setId(68);
		ss.newSurvey(user);
	}
	/**
	 * �л�״̬
	 */
	@Test
	public void toggleStatus() throws SQLException{
		
		ss.toggleStatus(1113);
	}
	
	/**
	 *  ��ѯ����
	 */
	@Test
	public void getSurvey() throws SQLException{
		
		ss.getSurvey(2);
	}
}
 