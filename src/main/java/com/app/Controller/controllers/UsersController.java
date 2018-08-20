package com.app.Controller.controllers;


import java.security.NoSuchAlgorithmException;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.Controller.entities.OldPassword;
import com.app.Controller.entities.Password;
import com.app.Controller.entities.Pharmacie;
import com.app.Controller.entities.Role;
import com.app.Controller.entities.Users;
import com.app.Controller.repository.PharmacieRepository;
import com.app.Controller.repository.RoleRepository;
import com.app.Controller.repository.UsersRepository;
import com.app.Controller.services.UserService;

@RestController
@RequestMapping("/user") 
@CrossOrigin(origins = "*", maxAge = 3600)
public class UsersController {
	@Autowired
	UsersRepository userRespository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	UserService userService;
	
	
	@Autowired
	PharmacieRepository pharmacieRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	/*ajouter utilisateur*/
	@PostMapping(value="/create" , consumes = "application/json", produces = "application/json")
	public Users createUser(@RequestBody Users user) throws NoSuchAlgorithmException  {
	
		
        
        String hashPWD = bCryptPasswordEncoder.encode(user.getMdp());
		user.setMdp(hashPWD);
	    return userRespository.save(user);
	    
        
	}
	

	/*ajouter utilisateur*/
	@PostMapping(value="/create/inscription" , consumes = "application/json", produces = "application/json")
	public Users createUserinscription(@RequestBody Users user) throws NoSuchAlgorithmException  {
	
		
        
        String hashPWD = bCryptPasswordEncoder.encode(user.getMdp());
		user.setMdp(hashPWD);
	    return userRespository.save(user);
        
	}
	
	
	/*retourner la liste des utilisateurs*/
	@GetMapping(value="/listUser")
	public List<Users> getAllUsers() {
		return userRespository.findAll();
		
	}
	
	/*modifier utilisateur*/
	@PutMapping("update/{id}")
	public Users updateUser(@PathVariable(value="id") int userId ,@RequestBody Users user) throws NoSuchAlgorithmException{
		Optional<Users> userr=userRespository.findById(userId);
		Users use= userr.get();
		use.setNom(user.getNom());
		use.setPrenom(user.getPrenom());
		use.setUsername(user.getUsername());
		
		use.setEmail(user.getEmail());
		
		String hashPWD = bCryptPasswordEncoder.encode(user.getMdp());
		use.setMdp(hashPWD); 
		
		if (user.getRole().getLibelle().equals("Administrateur")) 
		{
			use.setPharmacie(null);
			Role roleUpdate = roleRepository.findByLibelle(user.getRole().getLibelle());
			use.setRole(roleUpdate);
		}
		else
		{
        Role roleUpdate = roleRepository.findByLibelle(user.getRole().getLibelle());
        
        Pharmacie pharmacieUpdate = pharmacieRepository.findByNom(user.getPharmacie().getNom());
		 
		use.setRole(roleUpdate);
		use.setPharmacie(pharmacieUpdate);
		}
       
        
		return userRespository.save(use);
		
		
       
	}
	
	/*rechercher utilisateur byID*/
	@GetMapping("find/{id}")
	public Optional<Users> getUserById(@PathVariable(value="id") int userId) {
		return userRespository.findById(userId);
		
	}
	
	
	
	/*rechercher utilisateur by username*/
	@GetMapping("finduser/{username}")
	public Optional<Users> getUserByUsername(@PathVariable(value="username") String username) {
		
		return userRespository.findByUsername(username);
		
		
	}
	

	

	/*rechercher utilisateur byID*/
	@GetMapping("finduserr/{nom}/{prenom}")
	public Optional<List<Users>> findUserByNomAndPrenom(@PathVariable  String nom, @PathVariable String prenom) {
		
		return (Optional<List<Users>>) userRespository.findByNomAndPrenom(nom, prenom);
		
		
	}
	
	@GetMapping("finduserbylibelle/{libelle}")
	public Optional<List<Users>> findUserByProfil(@PathVariable  String libelle) {
		
		Role role= roleRepository.findByLibelle(libelle);
		Optional<List<Users>> users = userRespository.findByRole(role);
		return users;
		
		
	}
	


	/*supprimer utilisateur*/
	@DeleteMapping("delete/{id}")
	public void deleteUser(@PathVariable(value="id") int userId){
		userRespository.deleteById(userId);
		
	}
	
	
	
	
    

  
	
	
	@PostMapping(value = "/savePassword" )
	public Users savePassword( @RequestBody Password password
	  ) {
	    User user = 
	      (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	     
	    System.out.println(user);
	    Optional<Users> users= userRespository.findByUsername(user.getUsername());
	    Users use= users.get();
	    use.setMdp(bCryptPasswordEncoder.encode(password.getPassword()));
	   return userRespository.save(use);
	   
	       
	
	}
	
	
	@PostMapping(value = "/password" )
	public boolean savePassword( @RequestBody OldPassword password
	  ) {
	    User user = 
	      (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	     
	    System.out.println(user);
	    Optional<Users> users= userRespository.findByUsername(user.getUsername());
	    Users use= users.get();
	   
	    if (bCryptPasswordEncoder.matches(password.getPassword(), use.getMdp()))
	    {
	    	
	    	return true;
	    }
	    else
	    {
	    	return false;
	    }
	
	   
	       
	
	}
	
	
	

}
