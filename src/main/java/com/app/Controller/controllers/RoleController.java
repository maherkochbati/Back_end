package com.app.Controller.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.Controller.entities.Role;
import com.app.Controller.repository.RoleRepository;



@RestController
@RequestMapping("/role")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)

public class RoleController {
	@Autowired
	RoleRepository roleRepository;
	
	
	
	/*ajouter role*/
	@PostMapping(value="/create", consumes = "application/json", produces = "application/json")
	public Role createRole(@RequestBody Role role) {
		return roleRepository.save(role);
		
	}
	
	
	
	
	/*retourner la liste des roles*/
	@GetMapping(value="/listRole")
	public List<Role> getAllRoles() {
		return roleRepository.findAll();
		
	}
	
	/*retourner la liste des roles*/
	@GetMapping(value="/listRole/inscription")
	public List<Role> getRoles() {
		return roleRepository.findAll();
		
	}
	
	
	/*modifier role*/
	@PutMapping("/update/{id}")
	public Role updateRole(@PathVariable(value="id") int roleId ,@RequestBody Role role){
		role.setId(roleId);
		return roleRepository.save(role);
	}
	
	
	
	
	/*rechercher role byID*/
	@GetMapping("/find/{id}")
	public Optional<Role> getRoleById(@PathVariable(value="id") int roleId) {
		return roleRepository.findById(roleId);
	}
	
	
	
	
	
	/*supprimer role*/
	@DeleteMapping("/delete/{id}")
	public void  deleteRole(@PathVariable(value="id") int roleId){
		roleRepository.deleteById(roleId);
		
	}
	
	
	
}





