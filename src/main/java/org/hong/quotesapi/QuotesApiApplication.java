package org.hong.quotesapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author hong
 */
@SpringBootApplication
public class QuotesApiApplication {

	/**
	 * TODO
	 *
	 * Lib: log - update with more feature
	 * Lib: Rate limiter / API statistics / monitoring
	 * Lib: how to use event bus
	 *
	 */
	public static void main(String[] args) {
		SpringApplication.run(QuotesApiApplication.class, args);
	}
}
