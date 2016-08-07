package com.data.service;

import java.util.List;

import com.data.model.Answer;
import com.data.model.Page;
import com.data.model.Question;
import com.data.model.Survey;
import com.data.model.User;
/**
 * SurveyService
 * @author Administrator
 *
 */
public interface SurveyService {
	/*
	 * 查询调查列表
	 */
	public List<Survey> findMySurveys(User user);
	/*
	 * 新建调查
	 */
	public Survey newSurvey(User user);
	
	/*
	 * 按照id查询Survey
	 */
	public Survey getSurvey(Integer sid);
	
	/*
	 * 按照id查询Survey同时携带所有孩子
	 */
	public Survey getSurveyWithChildren(Integer sid);
	/*
	 * 更新调查
	 */
	public void updateSurvey(Survey model);
	/**
	 * 保存/更新页面
	 */
	public void saveOrUpdatePage(Page model);
	/**
	 * 按照id查询页面
	 */
	public Page getPage(Integer pid);
	/**
	 * 保存/更新问题
	 */
	public void saveOrUpdateQuestion(Question model);
	/**
	 * 删除问题，同时删除答案
	 */
	public void deleteQuestion(Integer qid);
	/**
	  * 删除页面,同时删除问题，答案
	 * @param pid
	 */
	public void deletePage(Integer pid);
	/**
	 * 删除调查,同时删除页面,问题，答案
	 */
	public void deleteSurvey(Integer sid);
	/**
	 * 编辑问题
	 */
	public Question getQuestion(Integer qid);
	/*
	 * 清除调查
	 */
	public void clearAnswers(Integer sid);
	/**
	 * 切换状态
	 * @param sid
	 */
	public void toggleStatus(Integer sid);
	/*
	 * 更新 logoPhoto路径
	 */
	public void updateLogoPhotoPath(Integer sid, String string);
	/*
	 * 查询所有可用的调查
	 */
	public List<Survey> findAllAvailableSurveys();
	/*
	 * 查询调查首页
	 */
	public Page getFirstPage(Integer sid);
	/*
	 * 获得上一页
	 */
	public Page getPrePage(Integer currPid);
	/*
	 * 获得下一页
	 */
	public Page getNextPage(Integer currPid);
	/*
	 * 批量保存答案
	 */
	public void saveAnswers(List<Answer> processAnswers);
	

	
	

}
