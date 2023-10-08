package org.gfg.securitydemo.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	@Autowired
	private MyUserRepository myUserRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		MyUser myUser1 = MyUser.builder().
				email("karan@gmail.com").
				password(passwordEncoder.encode("karan123")).
				authorities("dev").
				build();
		myUserRepository.save(myUser1);
		MyUser myUser2 = MyUser.builder().
				email("rashmi@gmail.com").
				password(passwordEncoder.encode("rashmi123")).
				authorities("qa").
				build();
		myUserRepository.save(myUser2);
	}
}
