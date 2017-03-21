package org.iakei.log.common;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class ReflectUtils {

	public static List<Class<?>> getInterfaceImplClass(Class<?> clazz,String packageName) {
		if (!clazz.isInterface())
			throw new IllegalArgumentException(clazz.getName() + " not interface");
		List<Class<?>> clazzList = new ArrayList<>();
		
		@SuppressWarnings("rawtypes")
		ArrayList<Class> allClass = getAllClass(packageName);
		for(Class<?> clazz1:allClass){
			if(clazz.isAssignableFrom(clazz1)){
				clazzList.add(clazz1);
			}
		}
		return clazzList;
	}

	/**
	 * 从一个指定路径下查找所有的类
	 * 
	 * @param name
	 */
	@SuppressWarnings("rawtypes")
	private static ArrayList<Class> getAllClass(String packagename) {
		ArrayList<Class> list = new ArrayList<>();
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		String path = packagename.replace('.', '/');
		try {
			ArrayList<File> fileList = new ArrayList<>();
			Enumeration<URL> enumeration = classLoader.getResources("../bin/" + path);
			while (enumeration.hasMoreElements()) {
				URL url = enumeration.nextElement();
				fileList.add(new File(url.getFile()));
			}
			for (int i = 0; i < fileList.size(); i++) {
				list.addAll(findClass(fileList.get(i), packagename));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 如果file是文件夹，则递归调用findClass方法，或者文件夹下的类 如果file本身是类文件，则加入list中进行保存，并返回
	 * 
	 * @param file
	 * @param packagename
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private static ArrayList<Class> findClass(File file, String packagename) {
		ArrayList<Class> list = new ArrayList<>();
		if (!file.exists()) {
			return list;
		}
		File[] files = file.listFiles();
		for (File file2 : files) {
			if (file2.isDirectory()) {
				assert !file2.getName().contains(".");// 添加断言用于判断
				ArrayList<Class> arrayList = findClass(file2, packagename + "." + file2.getName());
				list.addAll(arrayList);
			} else if (file2.getName().endsWith(".class")) {
				try {
					// 保存的类文件不需要后缀.class
					list.add(Class
							.forName(packagename + '.' + file2.getName().substring(0, file2.getName().length() - 6)));
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}
}
