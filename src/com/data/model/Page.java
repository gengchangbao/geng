package com.data.model;

import java.util.HashSet;
import java.util.Set;

/**
 * 页面类
 * @author Administrator
 *
 */
public class Page {

	private Integer id;
	private String title="未命名sadsa";
	private String description;//产品描述
	
	//页序
	private float orderno;
	
	public float getOrderno() {
		return orderno;
	}
	public void setOrderno(float orderno) {
		this.orderno = orderno;
	}
	//建立从Page到Survey之间多对一关联关系
	private Survey survey;
	//建立从Page到Question之间一对多关联关系
	private Set<Question> questions =new HashSet<Question>();
	
	public Set<Question> getQuestions() {
		return questions;
	}
	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}
	public Survey getSurvey() {
		return survey;
	}
	public void setSurvey(Survey survey) {
		this.survey = survey;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
			
}
