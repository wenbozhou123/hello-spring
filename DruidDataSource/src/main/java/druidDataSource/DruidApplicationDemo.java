package druidDataSource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@Slf4j
public class DruidApplicationDemo {

	public static void main(String[] args) {
		SpringApplication.run(DruidApplicationDemo.class, args);
	}

	@RequestMapping("/hello")
	public String hello(){
		return "hello spring";
	}

}