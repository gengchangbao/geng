package com.data.struts2.cation;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.data.model.User;
import com.data.service.UserService;
import com.data.util.DataUtil;
import com.data.util.ValidateUtil;
/**
 * 注册action
 * @author Administrator
 *
 */
@Controller
@Scope("prototype")//prototype圆形表示action是圆形bean 
public class RegAction extends BaseAction<User> {

	/**
	 * serialVersionUID用来反序列化的
	 */
	private static final long serialVersionUID = -3481683704661275602L;
	

	private  String confirmPassword;
	//注入userService
	@Resource
	private UserService userService;
	
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	/**
	 * 到达注册页面
	 * @return
	 * 跳过校验
	 */
	@SkipValidation
	public String toRegPage(){
		return "regPage";
	}
	/*
	 * 进行用户注册
	 */
	public String doReg(){
		model.setPassword(DataUtil.md5(model.getPassword()));
		userService.saveEntity(model);
		return SUCCESS;
	}
	/*
	 *校验
	 */
	public void validate(){
		//1.非空
		String email=model.getEmail();
		if(!ValidateUtil.isValid(email)){
			addFieldError("email", "email这是必填项！");
		}
		if(!ValidateUtil.isValid(model.getPassword())){
			addFieldError("password", "password这是必填项！");
		}
		if(!ValidateUtil.isValid(model.getNickName())){
			addFieldError("nickName", "nickName这是必填项！");
		}
		if(hasErrors()){
			return ;
		}
		//2.密码一致性
		if(!model.getPassword().equals(confirmPassword)){
			addFieldError("password", "密码不一致");
			return ;
		}
		//3.email占用
		if(userService.isRegisted(model.getEmail())){
			addFieldError("email", "email已占用");
		}
	}
}
