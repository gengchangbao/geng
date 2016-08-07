package com.data.service;

import java.util.List;
/**
 * ������dao�ӿ�
 * @author Administrator
 *
 * @param <T>
 */
public interface BaseService<T> {
	//д����
		public void saveEntity(T t);
		public void saveOrUpdateEntity(T t);//��������
		public void updateEntity(T t);
		public void detleteEntity(T t);
		public void batchEntityByHQL(String hql,Object...objects);//������--���������ɾ��������

		
		//������
		public T loadEntity(Integer id);
		public T getEntity(Integer id);
		public List<T> findEntityByHQL(String hql,Object...objects);
		
}
