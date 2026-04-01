package com.project.inventory.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.inventory.entity.User;

@Repository
public interface UserRepoInterface extends JpaRepository<User,Integer>{
 
	Optional<User> findByUserName(String userName);
 
}