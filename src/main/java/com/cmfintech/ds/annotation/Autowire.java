package com.cmfintech.ds.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**   
 * @Title: Autowire.java
 * @Description:自动注入注解
 * @Company  电子科技大学自动化研究所
 * @author  杜松   
 * @date 2017年12月19日 下午5:10:14
 * @version V1.0   
*/
@Target(ElementType.FIELD)//修饰变量
@Retention(RetentionPolicy.RUNTIME)
public @interface Autowire {
    public String id();

}