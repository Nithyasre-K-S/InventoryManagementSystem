package com.project.inventory.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class RegisterDTO {
	private String fullName;
    private String mobileNumber;    
    private String email; 
    private String userName;
	private String password;
	private String userRole;
 
}