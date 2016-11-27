package com.epam.amatias;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class CloudHashmapClientApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(CloudHashmapClientApplication.class).
				initializers(new SpringApplicationContextInitializer())
				.application()
				.run(args);
	}
}
