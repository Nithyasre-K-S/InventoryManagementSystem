package com.project.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.inventory.entity.Customer;

public interface CustomerRepoInterface extends JpaRepository<Customer, Long> {

}