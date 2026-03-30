package com.project.inventory.service;

import org.springframework.stereotype.Service;

import com.project.inventory.dto.LoginRequestDTO;
import com.project.inventory.dto.RegisterDTO;
import com.project.inventory.entity.User;

public interface UserServiceInterface {
	
	String updateUser(int id, User user);
	
	String deleteUser(int id);

	String createUser(RegisterDTO registerDto);

	String login(LoginRequestDTO request);
}
