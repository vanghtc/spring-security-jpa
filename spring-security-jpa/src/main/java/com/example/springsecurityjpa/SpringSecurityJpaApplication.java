package com.example.springsecurityjpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.springsecurityjpa.user.User;
import com.example.springsecurityjpa.user.UserRepository;

@SpringBootApplication
public class SpringSecurityJpaApplication  implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityJpaApplication.class, args);
	}

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public void run(String... args) throws Exception {
		User user = new User();
		user.setUserName("test");
		user.setPassword(passwordEncoder.encode("test"));
		
		userRepository.save(user);
		
	}

}
