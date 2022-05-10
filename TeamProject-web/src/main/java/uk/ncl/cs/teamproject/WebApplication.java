package uk.ncl.cs.teamproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 *
 * @author yantao xu
 */

@SpringBootApplication
@CrossOrigin(origins = {"http://127.0.0.1:8080", "null"})
@ComponentScan(basePackages = {"uk.ncl.cs.teamproject.*"})
public class WebApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
	}
}
