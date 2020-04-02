package org.szh.config;

import org.szh.core.DataSourceType;

/**
 * 数据源上下文切换，
 * ThreadLocal存储本地线程全局变量key
 * 保证线程安全
 * 
 * @author Terry Zi
 *
 */
public class DataSourceContextHolder {

	private static final ThreadLocal<String> DATASOURCE_LOCAL = new ThreadLocal<String>();

	public static void read() {
		DATASOURCE_LOCAL.set(DataSourceType.read.getType());
		System.out.println("数据库切换到读");

	}

	public static void write() {
		DATASOURCE_LOCAL.set(DataSourceType.write.getType());
		System.out.println("数据库切换到写");
	}

	public static String getReadOrWrite() {
		return DATASOURCE_LOCAL.get();
	}

	public static void clearReadOrWrite() {
		DATASOURCE_LOCAL.remove();
	}
}
