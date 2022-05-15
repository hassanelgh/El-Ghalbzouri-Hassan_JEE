package com.example.user;

import com.example.user.entities.Role;
import com.example.user.entities.User;
import com.example.user.service.HashPassword;
import com.example.user.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class UserApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}
	@Bean
	HashPassword getHashPassord(){
		return new HashPassword();
	}
	@Bean
	CommandLineRunner start(UserService userService){
		return args -> {
			User user=new User();
			user.setUsername("user1");
			user.setPassword("aeeeeeeeeeeeeeeeeeeeeeeeeee");
			User user1=new User();
			user1.setUsername("hassan");
			user1.setPassword("123456789");

			userService.addNewUser(user);
			userService.addNewUser(user1);
			Stream.of("STUDENT","ADMIN","USER").forEach(roleName->{
				Role role=new Role();
				role.setRoleName(roleName);
				userService.addNewRole(role);
			});
			userService.addRoleToUser("user1","STUDENT");
			userService.addRoleToUser("user1","USER");
			userService.addRoleToUser("hassan","ADMIN");

			userService.authentication("hassan","123456789");
		};

	}

}
