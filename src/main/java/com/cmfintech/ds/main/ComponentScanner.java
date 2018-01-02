package com.cmfintech.ds.main;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.cmfintech.ds.annotation.Component;

/**
 * @Title: ComponentScanner.java
 * @Description:包扫描器
 * @Company 电子科技大学自动化研究所
 * @author 杜松
 * @date 2017年12月19日 下午5:15:01
 * @version V1.0
 */
public class ComponentScanner {

	/**
	 * 
	 * @Description:获取包含Component注解的类名
	 * @param packageName
	 * @return
	 * @author 杜松
	 * @date 2017年12月19日 下午5:16:12
	 */
	public static HashMap<String, String> getComponentClassName(String packageName) {
		List<String> classes = getClassNameByPackageName(packageName);
		HashMap<String, String> components = new HashMap<String, String>();
		
		for (String string : classes) {
			
			Component component=null;
			try {
				 component=Class.forName(string).getAnnotation(Component.class);
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (component!=null) {
			components.put(component.id(),string);
			
			}
			
		}
			

		return components;
	}

	public static List<String> getClassNameByPackageName(String packageName) {
		String filePath = ClassLoader.getSystemResource("").getPath() + packageName.replace(".", "\\");
		List<String> fileNames = getClassNameByFilePath(filePath);
		return fileNames;
	}

	/**
	 * 
	 * @Description:根据包路径获取所有类名
	 * @param filePath 包路径
	 * @return 包含包和类名的路径
	 * @author 杜松
	 * @date 2017年12月19日 下午5:22:52
	 */
	private static List<String> getClassNameByFilePath(String filePath) {

			List<String> list = new LinkedList<String>();

		File file = new File(filePath);
		File[] files = file.listFiles();

		for (File file2 : files) {
			if (file2.isDirectory())
				list.addAll(getClassNameByFilePath(file2.getPath()));
			else {
				String childFilePath = file2.getPath();
				int start = childFilePath.indexOf("\\classes")+9;
				int end=childFilePath.lastIndexOf(".");
				String path=childFilePath.substring(start, end);
		
				path=path.replace("\\", ".");
				System.out.println(path);
				list.add(path);
			}
		}

		return list;
	}

	

}
