package com.data.dao;

import java.util.List;
/**
 * BaseDao�ӿ�
 * @author Administrator
 *
 * @param <T>
 */
public interface BaseDao<T> {
	//д����
	public void saveEntity(T t);
	public void saveOrUpdateEntity(T t);//��������
	public void updateEntity(T t);
	public void detleteEntity(T t);
	public void batchEntityByHQL(String hql,Object...objects);//������--���������ɾ��������

	
	//������
	public T loadEntity(Integer id);
	public T getEntity(Integer id);//����id��ѯҳ��
	public List<T> findEntityByHQL(String hql,Object...objects);
	
	
}
