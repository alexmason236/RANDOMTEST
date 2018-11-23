package com.zk.config;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages= {"com.zk.service"})
public class ContextConfig {
	
	@Bean(name="datasource",destroyMethod="close")
	public DataSource dataSource() {
		BasicDataSource dataSource=new BasicDataSource();
		dataSource.setUrl("jdbc:mysql://localhost:3306/randomaward");
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUsername("root");
		dataSource.setPassword("admin");
		return dataSource;
	}
	
	@Bean
	public SessionFactory getSession() {
		LocalSessionFactoryBean lsfb = new LocalSessionFactoryBean();
		lsfb.setDataSource(dataSource());
		lsfb.setPackagesToScan(new String[] { "com.zk.model" });
		Properties props = new Properties();
		props.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");
		props.setProperty("hibernate.show_sql", "true");
		props.setProperty("hibernate.hbm2ddl.auto", "update");
		lsfb.setHibernateProperties(props);
		try {
			lsfb.afterPropertiesSet();
		} catch (IOException e) {
			e.printStackTrace();
		}
		SessionFactory object = lsfb.getObject();
		return object;
	}
	
	@Bean
	public PlatformTransactionManager getTransactionM() {
		System.out.println(getSession());
	    HibernateTransactionManager transactionManager = new HibernateTransactionManager();
	    transactionManager.setSessionFactory(getSession());
	    return transactionManager;
	}
}
