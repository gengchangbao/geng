package com.data.service;

import com.data.model.User;
/**
 * UserService
 * @author Administrator
 *
 */
public interface UserService extends BaseService<User> {
	/**
	 * 判断email是否被占用
	 * @param email
	 * @return
	 */
	public	boolean isRegisted(String email);
	/*
	 * 验证登录信息
	 */
	public User validateLoginInfo(String email, String md5);

}
