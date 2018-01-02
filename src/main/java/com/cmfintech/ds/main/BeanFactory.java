package com.cmfintech.ds.main;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

import com.cmfintech.ds.annotation.Autowire;
import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * @Title: BeanFactory.java
 * @Description:用于注解解析的IOC容器
 * @Company 电子科技大学自动化研究所
 * @author 杜松
 * @date 2017年12月19日 下午5:13:26
 * @version V1.0
 */
public class BeanFactory {

	private HashMap<String, Object> beanPool;// IOC容器
	private HashMap<String, String> components;// 组件

	/**
	 * 构造器表示一初始化该类，即将组件扫描完毕，等待使用
	 */
	public BeanFactory(String packageName) {
		beanPool = new HashMap<String, Object>();

		scanComponents(packageName);
	}

	/**
	 * 
	 * @Description:组件扫描
	 * @param packageName
	 * @author 杜松
	 * @date 2017年12月20日 上午8:49:26
	 */
	private void scanComponents(String packageName) {
		components = ComponentScanner.getComponentClassName(packageName);
	}

	/**
	 * 
	 * @Description:IOC容器获取bean
	 * @param id
	 *            beanID
	 * @return bean实例
	 * @throws ClassNotFoundException
	 * @author 杜松
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @date 2017年12月20日 上午8:49:38
	 */
	public Object getBean(String id) throws InstantiationException, IllegalAccessException, ClassNotFoundException,
			NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		if (beanPool.containsKey(id)) {
			return beanPool.get(id);
		} else {
			if (components.containsKey(id)) {
				Object object = Class.forName(components.get(id)).newInstance();
				object = assemblyMember(object);
				beanPool.put(id, object);
				return getBean(id);
			} else {
				throw new RuntimeException("没有找到相应的类，请检查对应的BeanId：" + id + "是否拼写正确！");
			}
		}
	}

	/**
	 * 
	 * @Description:成员装配
	 * @param obj
	 * @return
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @author 杜松
	 * @date 2017年12月20日 上午9:03:38
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Object assemblyMember(Object obj) throws NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, InstantiationException, ClassNotFoundException {
		
		
		Class clazz = obj.getClass();
		for (Field field : clazz.getDeclaredFields()) {
			Autowire autowire = field.getAnnotation(Autowire.class);
			
			if (autowire != null) {
				Method method = clazz.getMethod(getSetMethodName(field.getName()),
						field.getType());
				
				method.invoke(obj,getBean(autowire.id()));

			}
		}
		return obj;
	}

	/**
	 * 
	  *@Description:拼接
	  *@param name
	  *@return
	  *@author  杜松   
	  *@date 2017年12月20日 上午10:02:42
	 */
	public static String getSetMethodName(String name) {
		return "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
	}

}