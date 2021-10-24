package com.begenerous;

import com.begenerous.model.Role;
import com.begenerous.model.User;
import com.begenerous.repository.RoleRepo;
import com.begenerous.repository.UserRepo;
import com.begenerous.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

import static com.begenerous.util.RoleName.ROLE_ADMIN;
import static com.begenerous.util.RoleName.ROLE_USER;

@SpringBootApplication
public class BeGenerousApplication {

	public static void main(String[] args) {
		SpringApplication.run(BeGenerousApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(UserService userService, UserRepo userRepo, RoleRepo roleRepo) {
		return args -> {
			/** Only for init
			userService.saveUser(new User(null, "john@email", "1234", "John Doe", "", Collections.emptyList()));
			userService.saveUser(new User(null, "alex@email", "1234", "Alex Doe", "", Collections.emptyList()));
			userService.saveUser(new User(null, "jane@email", "1234", "Jane Doe", "", Collections.emptyList()));

			userService.saveRole(new Role(null, ROLE_USER));
			userService.saveRole(new Role(null, ROLE_ADMIN));

			userService.addRoleToUser(userRepo.findByEmail("john@email").getUser_id(), roleRepo.findByName(ROLE_USER).getRoleId());
			userService.addRoleToUser(userRepo.findByEmail("john@email").getUser_id(), roleRepo.findByName(ROLE_ADMIN).getRoleId());
			userService.addRoleToUser(userRepo.findByEmail("alex@email").getUser_id(), roleRepo.findByName(ROLE_USER).getRoleId());
			userService.addRoleToUser(userRepo.findByEmail("jane@email").getUser_id(), roleRepo.findByName(ROLE_USER).getRoleId());
			 */
		};
	}

}
