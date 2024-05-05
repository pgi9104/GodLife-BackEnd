package com.gen.script;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @apiNote
 * 어플리케이션 시작
 * @author rbdlf
 *
 */
@EnableJpaAuditing
@SpringBootApplication
public class CommonApplication {
	/**
	 * @apiNote
	 * 어플리케이션 시작
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(CommonApplication.class, args);
	}
}