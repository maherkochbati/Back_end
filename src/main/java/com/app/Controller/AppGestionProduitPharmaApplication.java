package com.app.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.app.Controller.services.UserService;
import org.springframework.boot.CommandLineRunner;



@SpringBootApplication
public class AppGestionProduitPharmaApplication implements CommandLineRunner {
	
	
	
	@Autowired
	UserService userService;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(AppGestionProduitPharmaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	//@Override
	//public void run(String... args) throws Exception {

	 //Users user = new Users();
	 //user.setUsername("maher");
	//user.setMdp(bCryptPasswordEncoder.encode("maher"));
	 //user.setEmail("maher");
	 
	 //userService.createUser(user);

	//}

}
