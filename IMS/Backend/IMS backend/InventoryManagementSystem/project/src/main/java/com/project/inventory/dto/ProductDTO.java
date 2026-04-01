package com.project.inventory.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ProductDTO {


	    private String name;
	    private String brand;
	    private String size;
	    private double price;
	    private int quantity;


}
