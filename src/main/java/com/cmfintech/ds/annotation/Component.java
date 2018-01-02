package com.cmfintech.ds.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**   
 * @Title: Component.java
 * @Description:组件注解定义
 * @Company  电子科技大学自动化研究所
 * @author  杜松   
 * @date 2017年12月19日 下午5:05:09
 * @version V1.0   
*/
@Target(ElementType.TYPE)//指定注解可以修饰的目标
@Retention(RetentionPolicy.RUNTIME)//指定注解的生命周期
public @interface Component {
    public String id();
}