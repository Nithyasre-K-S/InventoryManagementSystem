package com.project.inventory.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.inventory.dto.CustomerDTO;
import com.project.inventory.entity.Customer;
import com.project.inventory.exceptionhandling.InventoryException;
import com.project.inventory.repository.CustomerRepoInterface;

@Service
public class CustomerService implements CustomerServiceInterface {

    private final CustomerRepoInterface customerRepo;

    public CustomerService(CustomerRepoInterface customerRepo) {
        this.customerRepo = customerRepo;
    }

    @Override
    public Customer saveCustomer(CustomerDTO dto) throws InventoryException {

        if (dto.getCustomerName() == null || dto.getCustomerName().isBlank()) {
            throw new InventoryException("Customer name cannot be empty");
        }

        if (dto.getPhoneNumber() == null || dto.getPhoneNumber().isBlank()) {
            throw new InventoryException("Phone number cannot be empty");
        }

        Customer customer = new Customer();
        customer.setCustomerName(dto.getCustomerName());
        customer.setPhoneNumber(dto.getPhoneNumber());

        return customerRepo.save(customer);
    }

    @Override
    public Customer getCustomerById(Long id) throws InventoryException {
        return customerRepo.findById(id)
                .orElseThrow(() -> new InventoryException("Customer not found with ID: " + id));
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }

    @Override
    public void deleteCustomer(Long id) throws InventoryException {
        if (!customerRepo.existsById(id)) {
            throw new InventoryException("Cannot delete. Customer not found with ID: " + id);
        }
        customerRepo.deleteById(id);
    }
}