package com.project.inventory.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ProductDTO {

    private String name;
    private int quantity;
    private double price;

}
