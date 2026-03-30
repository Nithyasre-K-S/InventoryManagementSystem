package com.project.inventory.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.inventory.dto.ProductDTO;
import com.project.inventory.entity.Product;
import com.project.inventory.exceptionhandling.InventoryException;
import com.project.inventory.service.ProductServiceInterface;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/products")
public class ProductController {


    private final ProductServiceInterface productService;
    
    public ProductController(ProductServiceInterface productService) {
        this.productService = productService;
    }

    @PostMapping
    public Product save(@RequestBody ProductDTO productdto, HttpSession session) {
//        String username = (String) session.getAttribute("USER_NAME");
//        String userrole=(String) session.getAttribute("USER_ROLE");
//        if(username==null)
//        {
//        	throw new InventoryException("Unauthorized Access!");
//        }
        return productService.saveProduct(productdto);
    }

   
    @PutMapping("/{id}")
    public Product update(@PathVariable Long id, @RequestBody Product product, HttpSession session) {
        String username = (String) session.getAttribute("USER_NAME");
        String userrole=(String) session.getAttribute("USER_ROLE");
        if(username==null)
        {
        	throw new InventoryException("Unauthorized Access!");
        }
        return productService.updateProduct(id, product);
    }

  
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id, HttpSession session) {
        String username = (String) session.getAttribute("USER_NAME");
        String userrole=(String) session.getAttribute("USER_ROLE");
        if(username==null)
        {
        	throw new InventoryException("Unauthorized Access!");
        }
        productService.deleteProduct(id);
        return "Product deleted successfully";
    }

 
    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id, HttpSession session) {
        String username = (String) session.getAttribute("USER_NAME");
        String userrole=(String) session.getAttribute("USER_ROLE");
        if(username==null)
        {
        	throw new InventoryException("Unauthorized Access!");
        }
        return productService.getProductById(id);
    }

   
    @GetMapping
    public List<Product> getAll( HttpSession session) {
        String username = (String) session.getAttribute("USER_NAME");
        String userrole=(String) session.getAttribute("USER_ROLE");
        if(username==null)
        {
        	throw new InventoryException("Unauthorized Access!");
        }
        return productService.getAllProducts();
    }
}