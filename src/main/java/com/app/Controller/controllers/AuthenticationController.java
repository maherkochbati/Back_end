package com.app.Controller.controllers;



import  com.app.Controller.security.JwtTokenUtil;

import  com.app.Controller.entities.AuthToken;
import com.app.Controller.entities.LoginUser;
import com.app.Controller.entities.Users;
import com.app.Controller.services.UserService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "token/generate-token", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody LoginUser loginUser) throws AuthenticationException {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getUsername(),
                        loginUser.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final Optional<Users> user = userService.findOne(loginUser.getUsername());
        final String token = jwtTokenUtil.generateToken(user.get());
        return ResponseEntity.ok(new AuthToken(token));
    }
    
    @RequestMapping(value="/check", method = RequestMethod.GET)
    public ResponseEntity test() {
    	return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @RequestMapping(value="/list", method = RequestMethod.GET)
    public List<Users> getList(){
    	return userService.getUsersList();
    	
    }

}
