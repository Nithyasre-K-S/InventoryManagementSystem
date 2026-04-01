package com.project.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.inventory.entity.Product;


@Repository
public interface ProductRepoInterface extends JpaRepository<Product,Long>{
	 
		
	 
	}