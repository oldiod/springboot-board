package board.configuration;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration

//application.properties를 사용할수 있도록 설정파일의 위치를 지정해줍니다.
@PropertySource("classpath:/application.properties") 
 
public class DatabaseConfiguration {

	
//---application.properties에 설정했던 데이터베이스 관련정보를 사용하도록 지정합니다.
//---여기서는 기본으로 제공되는 application.properties 하나만 지정했는데,
//---@propertySource 어노테이션을 추가해서 다른설정 파일도 사용할수있습니다.
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@Bean
	@ConfigurationProperties(prefix="spring.datasource.hikari")
	public HikariConfig hikariConfig() {
		return new HikariConfig();
	}
	
	@Bean
	public DataSource dataSource() throws Exception{
		return new HikariDataSource(hikariConfig());
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory
	(DataSource dataSource)throws Exception{
	SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean(); //스프링 sqlSessionFactory를 생성하기 위해서 팩토리빈을 사용합니다.
	sqlSessionFactoryBean.setDataSource(dataSource);//앞에서 만든 데이터 소스를 설정합니다.
	sqlSessionFactoryBean.setMapperLocations
	(applicationContext.getResources("classpath:/mapper/**/sql-*.xml"));//mybatis 매퍼 파일의 위치 설정
	sqlSessionFactoryBean.setConfiguration(mybatisConfig());
	return sqlSessionFactoryBean.getObject();	
	}
	
	@Bean
	public SqlSessionTemplate sqlSessionTemplate
	(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
	@Bean
	@ConfigurationProperties(prefix="mybatis.configuration")//어플리케이션 프로퍼티스 설정중 마이바티스 설정을 가져옵니다.
	public org.apache.ibatis.session.Configuration mybatisConfig(){
			return new org.apache.ibatis.session.Configuration();
			//가져온 마이바티스 설정을 자바 클래스로 만들어서 반환합니다.
	}
}
