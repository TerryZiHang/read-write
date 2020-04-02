package org.szh.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;
import org.szh.core.DataSourceType;

/**
 * 实现AbstractRoutingDataSource的逻辑，
 * 将targetDateSources加载到 resolvedDataSources
 * 
 * @author Terry Zi 
 *
 */
@Component
public class RoutingDataSource extends AbstractRoutingDataSource {
	
	@Autowired
	@Qualifier("writeDataSource")
	private DataSource writeDataSource;

	@Autowired
	@Qualifier("readDataSource")
	private DataSource readDataSource;

	@Override
	public void afterPropertiesSet() {
		//设置默认数据源
		this.setDefaultTargetDataSource(writeDataSource);
		Map<Object, Object> targetDataSources = new HashMap<>(2);
		//设置备选的数据源集合
		targetDataSources.put(DataSourceType.write.getType(), writeDataSource);
		targetDataSources.put(DataSourceType.read.getType(), readDataSource);
		this.setTargetDataSources(targetDataSources);
		// 执行原有的afterPropertiesSet方法，将TargetDataSources中的加载到resolvedDataSources中
		super.afterPropertiesSet();
	}

	/**
	 * @return 
	 * @description：决定当前数据源对应的key
	 */
	@Override
	protected Object determineCurrentLookupKey() {
		String typeKey = DataSourceContextHolder.getReadOrWrite();
		return typeKey;
	}
}
