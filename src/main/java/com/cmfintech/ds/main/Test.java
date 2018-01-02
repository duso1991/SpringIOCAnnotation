package com.cmfintech.ds.main;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import com.cmfintech.ds.annotation.Autowire;
import com.cmfintech.ds.service.BusinessObject;

/**
 * @Title: Test.java
 * @Description:
 * @Company 电子科技大学自动化研究所
 * @author 杜松
 * @date 2017年12月19日 下午5:23:44
 * @version V1.0
 */
public class Test {
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		String packageName="com.cmfintech.ds";
		BeanFactory beanFactory=new BeanFactory(packageName);
		BusinessObject businessObj= (BusinessObject) beanFactory.getBean("businessObject");
		businessObj.print();
		
		Object obj=Class.forName("com.cmfintech.ds.service.BusinessObject").newInstance();
		
		Class clazz = obj.getClass();
		for (Field field : clazz.getDeclaredFields()) {
			
		
			Autowire autowire = field.getAnnotation(Autowire.class);
			
			if (autowire != null) {
				System.out.println("要装配的变量："+field.getName());
				
			}
		}
		
		
	}
	
}
