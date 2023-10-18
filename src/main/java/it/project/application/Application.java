/**
 * Class Name: Application
 * Description: Main entry for the project
 * 
 * Author: He Shen
 * Date: 2023/8/16
 */

package it.project.application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	@GetMapping("/welcome")
	public String hello(@RequestParam(value = "name", defaultValue = "StuRequestHub") String name) {
		return String.format("Welcome to %s Official Website!", name);
	}
}