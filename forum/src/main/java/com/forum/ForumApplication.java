//REFERENCE (SPRING BOARD EXAMPLE) *************
//https://private.tistory.com/54?category=753861
//LOGBACK 사용
//https://jeong-pro.tistory.com/154
//https://aljjabaegi.tistory.com/413
//log4jdbc.log4j2로 SQL 쿼리문 로깅
//https://devofhwb.tistory.com/81
//Cookie 사용
//https://medium.com/@aaaalpooo/spring-framework-spring-boot-%EC%BF%A0%ED%82%A4-%EC%83%9D%EC%84%B1-%EB%B0%8F-%EC%A0%9C%EA%B1%B0-5aab0bf1ab67

package com.forum;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@MapperScan(value= {"com.forum.mapper"})
public class ForumApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForumApplication.class, args);
	}
	
	//sqlSessionFactory
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception{
		
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		
		sessionFactory.setDataSource(dataSource);
		return sessionFactory.getObject();
	}

}

