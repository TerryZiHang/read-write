package org.szh.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * 配置数据源
 * 
 * @author Terry Zi
 *
 */
@Configuration
public class DataSourceConfig {

	@Value("${spring.datasource.type}")
	private Class<? extends DataSource> dataSourceType;

	@Primary
	@Bean(name = "writeDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.master")
	public DataSource writeDataSource() {
		// return new DruidDataSource();
		return DataSourceBuilder.create().type(dataSourceType).build();
	}

	@Bean(name = "readDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.slaver")
	public DataSource readDataSourceOne() {
		// return new DruidDataSource();
		return DataSourceBuilder.create().type(dataSourceType).build();
	}

	/**
	 * 延伸到 多个从库的情况下
	 * 
	 * 
	 * @return
	 * @throws SQLException
	 */
	/*
	 * @Bean("readDataSources") 
	 * public List<DataSource> readDataSources() throws SQLException {
	 *  	List<DataSource> dataSources = new ArrayList<>();
	 * 		dataSources.add(readDataSourceOne());
	 *  	return dataSources;
	 * }
	 */

}
