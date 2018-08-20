package com.app.Controller.services;


import java.util.Arrays;

import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import com.app.Controller.repository.UsersRepository;
import com.app.Controller.entities.Users;
import com.app.Controller.services.UserService;


@Service(value= "userService")
public class UserServiceImpl implements UserService, UserDetailsService {
	
	@Autowired
	UsersRepository userRepository;

	@Autowired
	BCryptPasswordEncoder PasswordEncoder;
	
	@Override
	public Users createUser(Users user) {
		Optional<Users> localUser = userRepository.findByUsername(user.getUsername());
        System.out.println(localUser.isPresent() ? "Found" : userRepository.save(user));
        return null;
	}

	@Override
	public void save(Users user) {
		userRepository.save(user);
	}
	
	@Override
	public Optional<Users> findOne(String username) {
		Optional<Users> findUser = userRepository.findByUsername(username);
		return findUser;
	}

	@Override
	public List<Users> getUsersList() {
		List<Users> list = userRepository.findAll();
		return list;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Users> optionalUsers = userRepository.findByUsername(username);

        optionalUsers
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
       // return optionalUsers.map(CustomUserDetails::new).get();
        return new org.springframework.security.core.userdetails.User(optionalUsers.get().getUsername(), optionalUsers.get().getMdp(), getAuthority());
	}
	
	private List<SimpleGrantedAuthority> getAuthority() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}

	@Override
	public Optional<Users> findByEmail(String email) {

      return userRepository.findByEmail(email);
	}

	@Override
	public Optional<Users> findByResetToken(String resetToken) {

    return userRepository.findByResetToken(resetToken);
	}
	


	

	
	
	
	

}
