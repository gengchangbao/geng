package com.data.struts2.cation;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.data.model.Page;
import com.data.model.Question;
import com.data.service.SurveyService;
/**
 * 
 * QuestionAction
 */
@Controller
@Scope("prototype")
public class QuestionAction extends BaseAction<Question> {

	private static final long serialVersionUID = 4588662866242532051L;
	
	//注入SurveyService
	@Resource
	private SurveyService surveyService;
	
	private Integer sid;
	
	private Integer pid;
	
	private Integer qid;
	
	public Integer getQid() {
		return qid;
	}

	public void setQid(Integer qid) {
		this.qid = qid;
	}

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	/**
	 * 到达选择题型页面
	 */
	public String toSelectQuestionType(){
		return "selectQuestionTypePage";
	}
	/**
	 * 到达问题设计页面
	 */
	public String toDesignQuestionPage(){//toDsignQuestionPage
		return "" +model.getQuestionType();
	}
	/*
	 * 保存/更新saveOrUpdateQuestion
	 */
	public String saveOrUpdateQuestion(){
		Page p=new Page();
			p.setId(pid);
			//维护关联关系
			model.setPage(p);
			surveyService.saveOrUpdateQuestion(model);
			return "designSurveyAction";
	}
	/**
	 * 删除问题
	 */
	public String deleteQuestion(){
		surveyService.deleteQuestion(qid);
		return "designSurveyAction";
	}
	/**
	 * 编辑问题editQuestion
	 */
	public String editQuestion(){
		this.model=surveyService.getQuestion(qid);
		return "" +model.getQuestionType();
	}
}
