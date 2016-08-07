package com.data.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.data.dao.BaseDao;
import com.data.service.BaseService;
/**
 * 抽象的baseService,专门用于继承
 * @author Administrator
 *
 * @param <T>
 */
public abstract class BaseServiceImpl<T> implements BaseService<T> {
	
	private BaseDao<T> dao;
	
	//注入dao
	@Resource
	public void setDao(BaseDao<T> dao) {
		this.dao = dao;
	}

	public void saveEntity(T t) {
		dao.saveEntity(t);
	}

	public void saveOrUpdateEntity(T t) {
		dao.saveOrUpdateEntity(t);

	}

	public void updateEntity(T t) {
		dao.updateEntity(t);
	}

	public void detleteEntity(T t) {
		dao.detleteEntity(t);
	}

	public void batchEntityByHQL(String hql, Object... objects) {
		dao.batchEntityByHQL(hql, objects);

	}

	public T loadEntity(Integer id) {
		
		return dao.loadEntity(id);
	}

	public T getEntity(Integer id) {
		
		return dao.getEntity(id);
	}

	public List<T> findEntityByHQL(String hql, Object... objects) {
		
		return dao.findEntityByHQL(hql, objects);
	}

}
