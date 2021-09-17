package com.tarikukebede.userManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class UserManagerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserManagerApiApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}


//	@Bean
//	@Autowired
//	CommandLineRunner run(UserService userService){
//		return args -> {
//			userService.saveRole(new Role(null, "ROLE_ACT"));
//			userService.saveRole(new Role(null, "ROLE_MAKER"));
//			userService.saveRole(new Role(null, "ROLE_CHECKER"));
//			userService.saveRole(new Role(null, "ROLE_ADMIN"));
//
//			userService.saveUser(new AppUser(null, "Daniel Craig", "danny", "1234", new ArrayList<>()));
//			userService.saveUser(new AppUser(null, "Jonna Hill", "hill", "1234", new ArrayList<>()));
//			userService.saveUser(new AppUser(null, "Lewis Hamilton", "hamilton", "1234", new ArrayList<>()));
//			userService.saveUser(new AppUser(null, "Sarah Johns", "sarah", "1234", new ArrayList<>()));
//
//			userService.addRoleToUser("danny", "ROLE_ACT");
//			userService.addRoleToUser("hill", "ROLE_MAKER");
//			userService.addRoleToUser("hamilton", "ROLE_CHECKER");
//			userService.addRoleToUser("sarah", "ROLE_ACT");
//			userService.addRoleToUser("sarah", "ROLE_MAKER");
//			userService.addRoleToUser("sarah", "ROLE_CHECKER");
//		};
//	}
}
