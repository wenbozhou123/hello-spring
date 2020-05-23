package manualMutiDataSource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**多个数据源的手动配置*/
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class,
		DataSourceTransactionManagerAutoConfiguration.class, JdbcTemplateAutoConfiguration.class})
@Slf4j
public class MultiDataSourceApplicationDemo {

	public static void main(String[] args) {
		SpringApplication.run(MultiDataSourceApplicationDemo.class, args);
	}

	@Bean
	@ConfigurationProperties("foo.datasource")
	public DataSourceProperties fooDataSourceProperties(){
		return new DataSourceProperties();
	}

	@Bean
	public DataSource fooDataSource(){
		DataSourceProperties fooDataSourceProperties = fooDataSourceProperties();
		log.info("foo dataSource: {}", fooDataSourceProperties.getUrl());
		return fooDataSourceProperties.initializeDataSourceBuilder().build();
	}

	@Bean
	@Resource
	public PlatformTransactionManager fooTxManger(DataSource fooDataSource){
		return new DataSourceTransactionManager(fooDataSource);
	}


	@Bean
	@ConfigurationProperties("bar.datasource")
	public DataSourceProperties barDataSourceProperties(){
		return new DataSourceProperties();
	}

	@Bean
	public DataSource barDataSource(){
		DataSourceProperties barDataSourceProperties = barDataSourceProperties();
		log.info("bar dataSource: {}", barDataSourceProperties.getUrl());
		return barDataSourceProperties.initializeDataSourceBuilder().build();
	}

	@Bean
	@Resource
	public PlatformTransactionManager barTxManger(DataSource barDataSource){
		return new DataSourceTransactionManager(barDataSource);
	}

}
