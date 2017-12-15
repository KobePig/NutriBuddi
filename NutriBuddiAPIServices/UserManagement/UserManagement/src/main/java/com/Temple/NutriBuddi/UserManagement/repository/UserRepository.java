package com.Temple.NutriBuddi.UserManagement.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;


import com.Temple.NutriBuddi.UserManagement.model.User;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByEmail(String email);
	User findById(int id);
	User findByEmailAndPassword(String email, String password);

	@Transactional
	long deleteByEmail(String email);
}
