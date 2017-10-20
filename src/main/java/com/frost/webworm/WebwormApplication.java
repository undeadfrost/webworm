package com.frost.webworm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.frost.webworm.webmagic.mapper")
public class WebwormApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebwormApplication.class, args);
	}
}
