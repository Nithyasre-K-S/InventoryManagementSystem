package com.project.inventory.service;

import org.springframework.stereotype.Service;

import com.project.inventory.dto.LoginRequestDTO;
import com.project.inventory.dto.RegisterDTO;
import com.project.inventory.entity.User;
import com.project.inventory.exceptionhandling.InventoryException;
import com.project.inventory.repository.UserRepoInterface;

@Service
public class UserService implements UserServiceInterface {

    private final UserRepoInterface userRepo;

    public UserService(UserRepoInterface userRepo) {
        this.userRepo = userRepo;
    }

   
    @Override
    public String createUser(RegisterDTO registerDto) throws InventoryException {

        // Validate username unique
        if (userRepo.findByUserName(registerDto.getUserName()).isPresent()) {
            throw new InventoryException("Username already exists");
        }

        User user = new User();
        user.setUserName(registerDto.getUserName());
        user.setPassword(registerDto.getPassword());
        user.setUserRole(registerDto.getUserRole());
        user.setEmail(registerDto.getEmail());
        user.setFullName(registerDto.getFullName());
        user.setMobileNumber(registerDto.getMobileNumber());

        userRepo.save(user);

        return "User created successfully";
    }

 
    public String updateUser(int id, User updatedUser) throws InventoryException {

        User user = userRepo.findById(id)
                .orElseThrow(() -> new InventoryException("User not found with ID: " + id));

        user.setUserName(updatedUser.getUserName());
        user.setPassword(updatedUser.getPassword());
        user.setUserRole(updatedUser.getUserRole());
        user.setEmail(updatedUser.getEmail());
        user.setFullName(updatedUser.getFullName());
        user.setMobileNumber(updatedUser.getMobileNumber());

        userRepo.save(user);

        return "User updated successfully";
    }

 
    @Override
    public String deleteUser(int id) throws InventoryException {

        if (!userRepo.existsById(id)) {
            throw new InventoryException("User not found with ID: " + id);
        }

        userRepo.deleteById(id);

        return "User deleted successfully";
    }

   
    @Override
    public String login(LoginRequestDTO request) throws InventoryException {

        User user = userRepo.findByUserName(request.getUserName())
                .orElseThrow(() -> new InventoryException("Invalid username"));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new InventoryException("Invalid password");
        }

        if (!user.getUserRole().equalsIgnoreCase(request.getUserRole())) {
            throw new InventoryException("User role does not match");
        }

        return "Login Successfully";
    }
}