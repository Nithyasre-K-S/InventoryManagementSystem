package com.project.inventory.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.inventory.dto.ProductDTO;
import com.project.inventory.entity.Product;
import com.project.inventory.exceptionhandling.InventoryException;
import com.project.inventory.repository.ProductRepoInterface;

@Service
public class ProductService implements ProductServiceInterface {

    private final ProductRepoInterface productRepo;

    public ProductService(ProductRepoInterface productRepo){
        this.productRepo = productRepo;
    }

    @Override
    public Product saveProduct(ProductDTO productDto) throws InventoryException {

        if (productDto.getName() == null || productDto.getName().isBlank()) {
            throw new InventoryException("Product name cannot be empty");
        }

        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());
        product.setBrand(productDto.getBrand());
        product.setSize(productDto.getSize());

        return productRepo.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product updated) throws InventoryException {

        Product existing = productRepo.findById(id)
                .orElseThrow(() -> new InventoryException("Product not found with ID: " + id));

        existing.setName(updated.getName());
        existing.setQuantity(updated.getQuantity());
        existing.setPrice(updated.getPrice());

        return productRepo.save(existing);
    }

    @Override
    public void deleteProduct(Long id) throws InventoryException {

        if (!productRepo.existsById(id)) {
            throw new InventoryException("Cannot delete. Product not found with ID: " + id);
        }

        productRepo.deleteById(id);
    }

    @Override
    public Product getProductById(Long id) throws InventoryException {

        return productRepo.findById(id)
                .orElseThrow(() -> new InventoryException("Product not found with ID: " + id));
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    

    @Override
    public Product updateStock(Long id, int newQuantity) throws InventoryException {

        if (newQuantity < 0) {
            throw new InventoryException("Stock cannot be negative");
        }

        Product product = productRepo.findById(id)
                .orElseThrow(() -> new InventoryException("Product not found with ID: " + id));

        product.setQuantity(newQuantity);

        return productRepo.save(product);
    }

    @Override
    public boolean checkStock(Long id, int wantedQty) throws InventoryException {

        if (wantedQty <= 0) {
            throw new InventoryException("Requested quantity must be greater than zero");
        }

        Product product = productRepo.findById(id)
                .orElseThrow(() -> new InventoryException("Product not found with ID: " + id));

        return product.getQuantity() >= wantedQty;
    }

    @Override
    public boolean isLowStock(Long id) throws InventoryException {

        Product product = productRepo.findById(id)
                .orElseThrow(() -> new InventoryException("Product not found with ID: " + id));

        // Low stock threshold = 10
        return product.getQuantity() <= 10;
    }

}