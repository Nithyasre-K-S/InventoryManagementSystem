package com.project.inventory.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.inventory.dto.LoginRequestDTO;
import com.project.inventory.dto.RegisterDTO;
import com.project.inventory.entity.User;
import com.project.inventory.service.UserServiceInterface;

import jakarta.servlet.http.HttpSession;


@CrossOrigin(origins = "http://localhost:4000", allowCredentials = "true")
@RestController
public class UserController {

    private final UserServiceInterface userService;
 
    public UserController(UserServiceInterface userService) {
        this.userService = userService;
    }

    
    @PostMapping("/authentication/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginRequestDTO loginRequest,HttpSession session) {
    	
 
         session.setAttribute("USER_NAME", loginRequest.getUserName());
         session.setAttribute("USER_ROLE", loginRequest.getUserRole());
         session.setMaxInactiveInterval(30 * 60);
         userService.login(loginRequest);
    	
		return  ResponseEntity.ok("Signed in successfully as " + loginRequest.getUserRole());
    	
    }
    
    @PostMapping("/signout")
    public ResponseEntity<String> signOut(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("Signed out successfully");
    }
 
 
    @PostMapping("/register")
    public String registerUser(@RequestBody RegisterDTO registerDto ) {
        return userService.createUser(registerDto);
    }
 

    @PutMapping("/{id}")
    public String updateUser(@PathVariable int id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

 
    @DeleteMapping("/{id}")
       public String deleteUser(@PathVariable int id) {
           userService.deleteUser(id);
           return "User deleted successfully";
       }
 
 
}