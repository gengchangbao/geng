/**
 * 
 */
package com.data.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;






import com.data.dao.BaseDao;

/**
 * �����daoʵ��,ר�����ڼ̳�
 * @author Administrator
 *
 */
@SuppressWarnings("unchecked")
public abstract class BaseDaoImpl<T> implements BaseDao<T> {
	//ע��SessionFactory
	@Resource
	private  SessionFactory sf;
	
	private Class<T> clazz;
	
	public BaseDaoImpl(){
	//�õ����ͻ�����
	ParameterizedType	type=(ParameterizedType) this.getClass().getGenericSuperclass();
	clazz=(Class<T>) type.getActualTypeArguments()[0];
	}
	public void saveEntity(T t) {
		
		/*Session ss=sf.openSession();
				
		ss.save(t);
		ss.beginTransaction().commit();*/
		
	sf.getCurrentSession().save(t);//session��������ŵõ�session
	}

	
	public void saveOrUpdateEntity(T t) {
		
		sf.getCurrentSession().saveOrUpdate(t);
	}

	
	public void updateEntity(T t) {
		
		sf.getCurrentSession().update(t);
	}

	public void detleteEntity(T t) {
	
		sf.getCurrentSession().delete(t);
	}

	/**
	 * ����HQL��������������
	 */
	public void batchEntityByHQL(String hql, Object... objects) {
	Query q=sf.getCurrentSession().createQuery(hql);
	for(int i=0; i<objects.length;i++){
		q.setParameter(i, objects[i]);
	}
	q.executeUpdate();
	}

	
	public T loadEntity(Integer id) {
		
		return (T) sf.getCurrentSession().load(clazz, id);
	}

	
	public T getEntity(Integer id) {
	
		return (T) sf.getCurrentSession().get(clazz, id);
	}

	
	
	public List<T> findEntityByHQL(String hql, Object... objects) {
		Query q=sf.getCurrentSession().createQuery(hql);
		for(int i=0; i<objects.length;i++){
			q.setParameter(i, objects[i]);
		}
		
		return q.list();
	}

}
