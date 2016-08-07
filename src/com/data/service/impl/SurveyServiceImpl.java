package com.data.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import oracle.sql.DATE;

import org.springframework.stereotype.Service;

import com.data.dao.BaseDao;
import com.data.model.Answer;
import com.data.model.Page;
import com.data.model.Question;
import com.data.model.Survey;
import com.data.model.User;
import com.data.service.SurveyService;
/**
 * SurveyService实现
 * @author Administrator
 *  
 */
@Service("surveyService")
public class SurveyServiceImpl implements SurveyService{
	
	@Resource(name="surveyDao")
	private BaseDao<Survey> surveyDao;
	
	@Resource(name="pageDao")
	private BaseDao<Page> pageDao;
	
	@Resource(name="questionDao")
	private BaseDao<Question> questionDao;
	
	@Resource(name="answerDao")
	private BaseDao<Answer> answerDao;
	/**
	 * 查询调查集合
	 */
	public List<Survey> findMySurveys(User user) {
		String hql="from Survey s where s.user.id =?";		
		return surveyDao.findEntityByHQL(hql,user.getId());
	}
	/*
	 * 新建调查
	 */
	public Survey newSurvey(User user) {
		Survey s=new Survey();
		Page p=new Page();
		
		//设置关联关系
		s.setUser(user);
		p.setSurvey(s);
		s.getPages().add(p);
		
		surveyDao.saveEntity(s);
		pageDao.saveEntity(p);
		return s;
	}
	//按照id查询Survey
	public Survey getSurvey(Integer sid) {
		
		return surveyDao.getEntity(sid);
	}
	/*
	 * 按照id查询Survey同时携带所有孩子
	 */
	public Survey getSurveyWithChildren(Integer sid) {
		//Survey s=surveyDao.getEntity(sid);
		Survey s=this.getSurvey(sid);
		//强行初始化pages和questios集合
		for(Page p:s.getPages()){
			p.getQuestions().size();
		}
		return s;
	}
	/*
	 * 更新调查
	 * (non-Javadoc)
	 * @see com.data.service.SurveyService#updateSurvey(com.data.model.Survey)
	 */
	public void updateSurvey(Survey model) {
		surveyDao.updateEntity(model);
		
	}
	/**
	 * 保存/更新页面
	 */
	public void saveOrUpdatePage(Page model) {
		pageDao.saveOrUpdateEntity(model);
		
		
	}
	/**
	 * 按照id查询页面
	 */
	public Page getPage(Integer pid) {
		
		return pageDao.getEntity(pid);
	}
	/**
	 * 保存/更新问题
	 */
	public void saveOrUpdateQuestion(Question model) {
		questionDao.saveOrUpdateEntity(model);
	}
	/**
	 * 删除问题，同时删除答案
	 */
	public void deleteQuestion(Integer qid) {
		//1.删除答案 Answer
		String hql="delete from Answer a where a.questionId=?";
		answerDao.batchEntityByHQL(hql, qid);
		//2.删除问题
		 hql="delete from Question q where q.id=?";
		questionDao.batchEntityByHQL(hql, qid);
	}
	/**
	 * 删除页面,同时删除问题，答案
	 * @param pid
	 */
	public void deletePage(Integer pid) {
		//1.删除答案 Answer
		String hql="delete from Answer a where a.questionId in(select q.id from Question q where q.page.id=?)";
		answerDao.batchEntityByHQL(hql, pid);
		//2.删除问题
		hql="delete from Question q where q.page.id=?";
		questionDao.batchEntityByHQL(hql, pid);
		//3.删除页面
		 hql="delete from Page p where p.id=?";
		pageDao.batchEntityByHQL(hql, pid);
		
	}
	/**
	 * 删除调查,同时删除页面,问题，答案
	 */
	public void deleteSurvey(Integer sid) {
		//删除答案
		String hql="delete from Answer a where a.surveyId=?";
		answerDao.batchEntityByHQL(hql, sid);
		//删除问题
		//hibernate在写操作中,不允许两级以上的链接.
		//hql = "delete from Question q where q.page.survey.id = ?" ;
		hql="delete from Question q where q.page.id in (select p.id from Page p where p.survey.id= ?)";
		questionDao.batchEntityByHQL(hql, sid);
		//删除页面
		hql="delete from Page p where p.survey.id= ?";
		pageDao.batchEntityByHQL(hql, sid);
		//删除调查
		hql="delete from Survey s where s.id= ?";
		surveyDao.batchEntityByHQL(hql, sid);
		
		
	}
	/**
	 * 编辑问题
	 */
	public Question getQuestion(Integer qid) {
		
		return questionDao.getEntity(qid);
	}
	/*
	 * 清除调查
	 */
	public void clearAnswers(Integer sid) {
		
		//删除答案
		String hql="delete from Answer a where a.surveyId=?";
		answerDao.batchEntityByHQL(hql, sid);
	}
	/**
	 * 切换调查状态
	 */
	public void toggleStatus(Integer sid) {
		Survey s=this.getSurvey(sid);
	String hql="update Survey s set s.closed=? where s.id= ?";
	surveyDao.batchEntityByHQL(hql,!s.isClosed(), sid);//批处理--批量保存和删除都可以
	}
	/*
	 *更新 logoPhoto路径
	 */
	public void updateLogoPhotoPath(Integer sid, String path) {
	
		String hql="update Survey s set s.logoPhotoPath=? where s.id=?";
		surveyDao.batchEntityByHQL(hql, path,sid);
		
	}
	/*
	 * 查询所有可用的调查
	 */
	public List<Survey> findAllAvailableSurveys() {
		String hql="from Survey s where s.closed=?";
		
		return surveyDao.findEntityByHQL(hql, false);
	}
	/**
	 * 查询调查首页
	 */
	public Page getFirstPage(Integer sid) {
		String hql="from Page p where p.survey.id=? order by p.orderno asc";
		List<Page> list=pageDao.findEntityByHQL(hql, sid);
		Page p=list.get(0);
		p.getQuestions().size();//初始化问题集合
		p.getSurvey().getTitle();//初始化调查对象
		return p;
	}
	/**
	 * 查询指定页面的上一页
	 */
	private Page getPrePage(Page targPage) {
		String hql = "from Page p where p.survey.id = ? and p.orderno < ? order by p.orderno desc" ;
		List<Page> list = pageDao.findEntityByHQL(hql, targPage.getSurvey().getId(),targPage.getOrderno());
		return list.get(0);
	}
	/**
	 * 查询指定页面的下一页
	 */
	private Page getNextPage(Page targPage) {
		String hql = "from Page p where p.survey.id = ? and p.orderno > ? order by p.orderno asc" ;
		List<Page> list = pageDao.findEntityByHQL(hql, targPage.getSurvey().getId(),targPage.getOrderno());
		return list.get(0);
	}
	/**
	 * 获得上一页
	 */
	public Page getPrePage(Integer currPid) {
		Page p=this.getPage(currPid);
		p=this.getPrePage(p);
		p.getQuestions().size();
		
		return p;
	}
	/**
	 * 获得下一页
	 */
	public Page getNextPage(Integer currPid) {
		Page p=this.getPage(currPid);
		p=this.getNextPage(p);
		p.getQuestions().size();
		
		return p;
	}
	/*
	 * 批量保存答案
	 */
	public void saveAnswers(List<Answer> list) {
		Date date =new Date();
		String uuid=UUID.randomUUID().toString();
		for(Answer a :list){
			a.setUuid(uuid);
			a.setAnswerTime(date);
			answerDao.saveEntity(a);
		}
	}
	
	

}
