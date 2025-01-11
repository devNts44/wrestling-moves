package com.wrestling_moves;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.wrestling_moves"})
@EntityScan("com.wrestling_moves.entity")
@EnableJpaRepositories("com.wrestling_moves.repository")
public class WrestlingMovesAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(WrestlingMovesAppApplication.class, args );

	}

}
