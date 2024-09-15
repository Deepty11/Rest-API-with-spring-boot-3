package com.example.webSecurityRESTAPI;

import com.example.webSecurityRESTAPI.enums.Role;
import com.example.webSecurityRESTAPI.model.User;
import com.example.webSecurityRESTAPI.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class WebSecurityRestapiApplication {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(UserService userService) {
		return args -> {
			userService.save(new User("Rehnuma Reza", "bakbik", "1234", Role.ADMIN));
			userService.save(new User("Rehnuma Reza", "bakbik22", "1234", Role.USER));

		};
	}

	public static void main(String[] args) {
		SpringApplication.run(WebSecurityRestapiApplication.class, args);
	}

}
