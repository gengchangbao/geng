package com.data.struts2.cation;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.data.model.User;
import com.data.service.UserService;
import com.data.util.DataUtil;
/**
 * 登录action
 * @author Administrator
 *
 */
@Controller
@Scope("prototype")
public class LoginAction extends BaseAction<User> implements SessionAware{
	
	private static final long serialVersionUID = -6212949546643479856L;
	@Resource
	private UserService userService;
	//接受session的Map
	private Map<String,Object> sessionMap;
	/**
	 * 到达登录页面
	 */
	public String toLoginPage(){
		return "loginPage";
	}
	/*
	 * 进行登录处理
	 */
	public String doLogin(){
		return "success";
	}
	/*
	 * validateDoLogin
	 * 加do的作用是特出命名。只在摸个方法上进行验证。 avlidate是前缀
	 * 校验登录信息
	 * (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#validate()
	 */
	public void validateDoLogin(){
		User user=userService.validateLoginInfo(model.getEmail(),DataUtil.md5(model.getPassword()));
		if(user==null){
			addActionError("email/password错误");
		}else{
			sessionMap.put("user", user);
		}
	}
	//注入session的map
	public void setSession(Map<String, Object> arg0) {
		this.sessionMap=arg0;
		
	}
}
