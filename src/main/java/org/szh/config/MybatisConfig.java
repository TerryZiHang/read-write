package org.szh.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

/**
 * mybatis配置
 * 
 * @author Terry Zi
 *
 */
@Configuration
@MapperScan("org.szh.mapper")
public class MybatisConfig {

	private static final String MAPPER_FILE = "classpath:/mybatis/*.xml";
	
	@Bean
	public SqlSessionFactory sqlSessionFactory(RoutingDataSource routingDataSource) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(routingDataSource);
		// mybatis配置文件
		// sqlSessionFactoryBean.setConfigLocation(new ClassPathResource(CONFIG_FILE));
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		// mybatis映射文件
		sqlSessionFactoryBean.setMapperLocations(resolver.getResources(MAPPER_FILE));
		return sqlSessionFactoryBean.getObject();
	}
}
