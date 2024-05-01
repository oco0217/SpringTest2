package com.ezen.www.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

//Spring에게 설정파일인것을 인지시키게 요청
@Configuration
@MapperScan(basePackages = {"com.ezen.www.repository"})
@EnableTransactionManagement
@EnableScheduling
public class RootConfig {

	//spring 것으로 가져와야됌
	
	@Autowired
	ApplicationContext applicationContext;
	
	
	@Bean
	public DataSource dataSource() {
		
		HikariConfig hikariConfig = new HikariConfig();
		
		//log4jdbc => DB의 로그를 찍을 수 있는 드라이버로 설정 변경
		
		hikariConfig.setDriverClassName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
		hikariConfig.setJdbcUrl("jdbc:log4jdbc:mysql://localhost:3306/springtest");
		hikariConfig.setUsername("springUser");
		hikariConfig.setPassword("mysql");
		
		//--------여기서부터 hikari 추가 설정----------
		hikariConfig.setMaximumPoolSize(5); //pool을 최대로 몇개 구현할지 최대 커넥션 개수
		hikariConfig.setMinimumIdle(5); //최소 유휴 커넥션 개수(Max와 같은 갯수로 설정)
		
		hikariConfig.setConnectionTestQuery("SELECT NOW()"); //test 쿼리문
		hikariConfig.setPoolName("springHikariCP");
		
		//추가설정
		//cachePrepStmts : cache 사용 여부 설정
		hikariConfig.addDataSourceProperty("dataSource.cachePrepStmts", "true");
		
		//mysql 드라이버가 연결당 cache 사이즈 : 250 ~ 500 사이 권장
		hikariConfig.addDataSourceProperty("dataSource.prepStmtsCacheSize", "250");
		
		//connection 당 캐싱할 preparedStatement의 개수 지정 옵션 : default 250
		hikariConfig.addDataSourceProperty("dataSource.prepStmtsCacheSqlLimit", "true"); //기본값 설정 (256)
		
		//mysql 서버에서 최신 이슈가 있을 경우 지원을 받을 것인지 설정
		hikariConfig.addDataSourceProperty("dataSource.useServerPrepStmts", "true");
		
		HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig);
		
		return hikariDataSource;
	}
	
	//메소드 자체를 Bean으로 설정할때 @Bean 
	//클래스를 객체로 만들어줄때는 @Component
	
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
	
		SqlSessionFactoryBean sqlFactoryBean = new SqlSessionFactoryBean();
		
		sqlFactoryBean.setDataSource(dataSource());
		sqlFactoryBean.setMapperLocations(
				applicationContext.getResources("classpath:/mappers/*.xml"));
		
		//DB : _(스네이크 표기법) / java : 카멜표기법
		//file_name => fileName
		//별칭 설정 : resultType이 너무 길때 별칭을 붙혀 Mppaer resultType에 별칭을 사용
		
		sqlFactoryBean.setConfigLocation(
				applicationContext.getResource("classpath:/MybatisConfig.xml"));
		
		
		return sqlFactoryBean.getObject();
	}
	
	//트랙잭션 매니저 설정
	@Bean
	public DataSourceTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}
	
	
}








