package com.data.struts2.Interceptor;

import com.data.model.User;
import com.data.struts2.UserAware;
import com.data.struts2.cation.BaseAction;
import com.data.struts2.cation.LoginAction;
import com.data.struts2.cation.RegAction;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
/*
 * 登录拦截器
 */
public class LoginInterceptor implements Interceptor {

	/**
	 *反序列化
	 *serialVersionUID作用： 
     *序列化时为了保持版本的兼容性，即在版本升级时反序列化仍保持对象的唯一性。
	 *有两种生成方式：
     *一个是默认的1L，比如：private static final long serialVersionUID = 1L;
     *一个是根据类名、接口名、成员方法及属性等来生成一个64位的哈希字段，比如：
     * private static final   long     serialVersionUID = xxxxL;
	 */
	private static final long serialVersionUID = 4459758785338312955L;

	public void destroy() {
		
	}

	public void init() {
		

	}

	public String intercept(ActionInvocation arg0) throws Exception {
		@SuppressWarnings("rawtypes")
		BaseAction action=(BaseAction) arg0.getAction();
		if(action instanceof LoginAction
				||action instanceof RegAction){
			return arg0.invoke();
		}else{
			User user=(User) arg0.getInvocationContext().getSession().get("user");
			if(user==null){
				//去登陆
				return "login";
			}else{
				//放行
				if(action instanceof UserAware){
					//注入user给action
					((UserAware) action).setUser(user);
				}
				return arg0.invoke();
			}
		}
		
	}

}
