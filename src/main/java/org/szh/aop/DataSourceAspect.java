package org.szh.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;
import org.szh.config.DataSourceContextHolder;

/**
 * 切换数据源的切面,设置优先级高于事务切面
 * 
 * @author Terry Zi 
 *
 */
@Aspect
@EnableAspectJAutoProxy(exposeProxy = true,proxyTargetClass=true)
@Component
public class DataSourceAspect implements PriorityOrdered{
	
	
	@Before("@annotation(org.szh.annotation.ReadDataSource) && execution(* org.szh.service.impl..*.*(..))")
	public void setReadDataSource() {
		// 如果已经开启事务了，就从主（写）数据库进行读
		DataSourceContextHolder.read();
	}
	
	@Before("@annotation(org.szh.annotation.WriteDataSource) && execution(* org.szh.service.impl..*.*(..))")
	public void setWriteDataSource() {
		DataSourceContextHolder.write();
	}

	/**
	 * @return 
	 * @description:优先级 ，值越小越优先
	 */
	@Override
	public int getOrder() {
		return 1;
	}

}
