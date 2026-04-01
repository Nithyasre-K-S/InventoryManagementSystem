package com.project.inventory.service;

import java.util.List;

import com.project.inventory.dto.ProductDTO;
import com.project.inventory.entity.Product;

public interface ProductServiceInterface {

    Product saveProduct(ProductDTO productdto);

    Product updateProduct(Long id, Product product);

    void deleteProduct(Long id);

    Product getProductById(Long id);

    List<Product> getAllProducts();
    

    Product updateStock(Long id, int newQty);
    boolean checkStock(Long id, int requiredQty);
    boolean isLowStock(Long id);

    
    
}