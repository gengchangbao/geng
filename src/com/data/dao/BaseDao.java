package com.data.dao;

import java.util.List;
/**
 * BaseDao接口
 * @author Administrator
 *
 * @param <T>
 */
public interface BaseDao<T> {
	//写操作
	public void saveEntity(T t);
	public void saveOrUpdateEntity(T t);//保存或更新
	public void updateEntity(T t);
	public void detleteEntity(T t);
	public void batchEntityByHQL(String hql,Object...objects);//批处理--批量保存和删除都可以

	
	//读操作
	public T loadEntity(Integer id);
	public T getEntity(Integer id);//按照id查询页面
	public List<T> findEntityByHQL(String hql,Object...objects);
	
	
}
