package com.app.Controller.services;


import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.User;

import com.app.Controller.entities.Users;

public interface UserService {
	
	
	public Users createUser(Users user);
	public Optional<Users> findOne(String username);
	public List<Users> getUsersList();
	public void save(Users user);
	public Optional<Users> findByEmail(String email);
	public Optional<Users> findByResetToken (String resetToken);
}
