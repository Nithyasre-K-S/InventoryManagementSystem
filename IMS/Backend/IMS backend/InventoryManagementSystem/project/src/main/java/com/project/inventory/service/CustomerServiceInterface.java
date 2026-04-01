package com.project.inventory.service;

import com.project.inventory.dto.CustomerDTO;
import com.project.inventory.entity.Customer;
import com.project.inventory.exceptionhandling.InventoryException;

import java.util.List;

public interface CustomerServiceInterface {

    Customer saveCustomer(CustomerDTO dto) throws InventoryException;

    Customer getCustomerById(Long id) throws InventoryException;

    List<Customer> getAllCustomers();

    void deleteCustomer(Long id) throws InventoryException;
}