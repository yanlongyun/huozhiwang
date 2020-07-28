package com.zrzhen.huozhiwang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.zrzhen.huozhiwang.dao")
@SpringBootApplication
public class HuozhiwangApplication {

	public static void main(String[] args) {
		SpringApplication.run(HuozhiwangApplication.class, args);
	}

}
