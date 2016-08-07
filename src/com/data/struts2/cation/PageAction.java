package com.data.struts2.cation;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.data.model.Page;
import com.data.model.Survey;
import com.data.service.SurveyService;
/**
 * PageAction
 */
@Controller
@Scope("prototype")
public class PageAction extends BaseAction<Page> {

	/**
	 * 实现反序列化
	 */
	private static final long serialVersionUID = 6877877081182545825L;
	
	//注入surveyService
	@Resource
	private SurveyService surveyService;
	private Integer sid;
	private Integer pid;
	
	
	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	/**
	 * 到达添加page的页面
	 */
	public String toAddPage(){
		return "addPagePage";
	}
	/**
	 * 编辑页面
	 */
	public String editPage(){
		this.model=surveyService.getPage(pid);
		return "editPagePage";
	}
	/**
	 * 保存/更新页面
	 */
	public String saveOrUpdatePage(){
		//维护关联关系
		Survey s=new Survey();
		s.setId(sid);
		model.setSurvey(s);
		surveyService.saveOrUpdatePage(model);
		return "designSurveyAction";
	}
	/**
	 * 删除页面
	 */
	public String deletePage(){
		surveyService.deletePage(pid);
		return "designSurveyAction";
	}
}
