package com.app.Controller.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.Controller.entities.Role;


public interface RoleRepository extends JpaRepository<Role, Integer> {
	
	Role findByLibelle(String role);

	

}
