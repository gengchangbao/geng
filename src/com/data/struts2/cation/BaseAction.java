package com.data.struts2.cation;

import java.lang.reflect.ParameterizedType;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public abstract class BaseAction<T> extends ActionSupport implements
		ModelDriven<T>, Preparable {

	/**
	 * 抽象action，专门用于继承
	 */
	private static final long serialVersionUID = -2937673330221749459L;
	public T model;
	public BaseAction(){
		try {//getClass().getGenericSuperclass()返回表示此 Class 所表示的实体（类、接口、基本类型或 void）的直接超类的 Type
			//getActualTypeArguments()返回表示此类型实际类型参数的 Type 对象的数组。
			//[0]就是这个数组中第一个了
			//newIntance()就是对象的实例化，返回的一个对象，如果知道具体对象的时很么的话，也可以直接用该对象来接受 A a = c.newInstance()  


			ParameterizedType type=(ParameterizedType) this.getClass().getGenericSuperclass();
			Class clazz=(Class) type.getActualTypeArguments()[0];
			model=(T) clazz.newInstance();
		} catch (Exception e) {
			
			e.printStackTrace();
		} 
	}
	public void prepare() throws Exception {//预处理方法
		

	}

	public T getModel() {//模型启动预处理的方法
 		return model;
	}
}
