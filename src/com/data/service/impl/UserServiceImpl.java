package com.data.service.impl;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.data.dao.BaseDao;
import com.data.model.User;
import com.data.service.UserService;
import com.data.util.ValidateUtil;
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements
		UserService {

	@Resource(name="userDao")
	public void setDao(BaseDao<User> dao) {
		
		super.setDao(dao);
	}
	/**
	 * 判断email是否被占用
	 * @param email
	 * @return
	 */
	public	boolean isRegisted(String email){
		String hql="from User u where u.email=?";
		List<User> list=this.findEntityByHQL(hql, email);
		return ValidateUtil.isValid(list);
	}
	/*
	 * 验证登录信息
	 */
	public User validateLoginInfo(String email, String md5) {
		String hql="from User u where u.email=? and u.password=?";
		List<User> list=this.findEntityByHQL(hql, email,md5);
		
		return ValidateUtil.isValid(list)?list.get(0):null;
	}

}
