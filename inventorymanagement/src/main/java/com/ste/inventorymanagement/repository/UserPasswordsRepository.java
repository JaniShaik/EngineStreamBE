package com.ste.inventorymanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ste.inventorymanagement.model.UserPasswords;

public interface UserPasswordsRepository  extends JpaRepository<UserPasswords, Long> {
	
	@Query("SELECT u FROM UserPasswords u where u.userId.id = :userId")
	public List<UserPasswords> getUserPasswordsByUserId(@Param("userId") Long userId);

	@Query("SELECT u FROM UserPasswords u ORDER BY u.createdAt ASC")
	public List<UserPasswords> getUserPasswordsByDateOrder();
}
