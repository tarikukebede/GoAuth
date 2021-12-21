package com.GoSchool.Auth;

import com.GoSchool.Auth.Models.AppUser;
import com.GoSchool.Auth.Models.Role;
import com.GoSchool.Auth.Services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@SpringBootApplication
@EnableSwagger2
public class AuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class, args);
	}


	@Bean
	CommandLineRunner run(UserService userService){
		return args -> {
			userService.saveRole(new Role(null, "ROLE_USER"));
			userService.saveRole(new Role(null, "ROLE_ADMIN"));
			userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));
			userService.saveRole(new Role(null, "ROLE_PROJECT_USER"));

			userService.saveUser(new AppUser(null, "user1", "1234",new ArrayList<>()));
			userService.saveUser(new AppUser(null, "user2", "1234", new ArrayList<>()));
			userService.saveUser(new AppUser(null, "user3", "1234", new ArrayList<>()));
			userService.saveUser(new AppUser(null, "user4", "1234", new ArrayList<>()));


			userService.addRoleToUser("user1", "ROLE_USER");
			userService.addRoleToUser("user2", "ROLE_ADMIN");
			userService.addRoleToUser("user3", "ROLE_PROJECT_USER");
			userService.addRoleToUser("user4", "ROLE_SUPER_ADMIN");
			userService.addRoleToUser("user4", "ROLE_ADMIN");

		};
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
