package com.project.inventory.controller;

import com.project.inventory.dto.CustomerDTO;
import com.project.inventory.entity.Customer;
import com.project.inventory.exceptionhandling.InventoryException;
import com.project.inventory.service.CustomerServiceInterface;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerServiceInterface customerService;

    @PostMapping
    public Customer saveCustomer(@RequestBody CustomerDTO dto) throws InventoryException {
        return customerService.saveCustomer(dto);
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Long id) throws InventoryException {
        return customerService.getCustomerById(id);
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @DeleteMapping("/{id}")
    public String deleteCustomer(@PathVariable Long id) throws InventoryException {
        customerService.deleteCustomer(id);
        return "Customer deleted successfully!";
    }
}