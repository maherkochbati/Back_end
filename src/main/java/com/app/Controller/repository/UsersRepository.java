package com.app.Controller.repository;



import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.Controller.entities.Role;
import com.app.Controller.entities.Users;

public interface UsersRepository extends JpaRepository<Users, Integer> {

	public Optional<Users> findByUsername (String username);
	public Optional<List<Users>> findByNomAndPrenom (String nom, String prenom);
	public Optional<List<Users>> findByRole (Role role);
	public Optional<Users> findByEmail (String email);
	public Optional<Users> findByResetToken (String resetToken);
}
