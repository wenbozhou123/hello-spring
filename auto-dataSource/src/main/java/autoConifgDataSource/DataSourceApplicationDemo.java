package autoConifgDataSource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**数据源的配置*/
@SpringBootApplication
@Slf4j
public class DataSourceApplicationDemo implements CommandLineRunner{

	@Qualifier("dataSource")
	@Autowired
	private DataSource dataSource;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public static void main(String[] args) {
		SpringApplication.run(DataSourceApplicationDemo.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		showConnections();
		showData();
	}

	private void showConnections() throws SQLException {
		log.info("dataSource:{}",dataSource.toString());
		Connection conn = dataSource.getConnection();
		log.info("con: {}",conn.toString());
		conn.close();
	}

	private void showData(){
		jdbcTemplate.queryForList("SELECT * from FOO")
		.forEach(row -> log.info(row.toString()));
	}
}
