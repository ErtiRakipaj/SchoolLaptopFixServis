package com.task.SchoolLaptopFixServis;

import com.task.SchoolLaptopFixServis.models.Role;
import com.task.SchoolLaptopFixServis.models.User;
import com.task.SchoolLaptopFixServis.repositories.RoleRepository;
import com.task.SchoolLaptopFixServis.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class SchoolLaptopFixServisApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchoolLaptopFixServisApplication.class, args);
	}
	@Bean
	CommandLineRunner run(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncode){
		return args ->{
			if(roleRepository.findRoleByAuthority("ADMIN").isPresent()) return;
			Role adminRole = roleRepository.save(new Role("ADMIN"));
			roleRepository.save(new Role("USER"));

			Set<Role> roles = new HashSet<>();
			roles.add(adminRole);

			User admin = new User("admin", passwordEncode.encode("password"), roles);

			userRepository.save(admin);
		};
	}

}
