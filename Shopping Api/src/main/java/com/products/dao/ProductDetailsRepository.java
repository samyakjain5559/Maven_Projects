package com.products.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDetailsRepository extends JpaRepository<ProductDetails, String>{
	//2 inputs in jpa repository 1st tell were the table match is for the DB , 2nd tell id of
	// main key
	
	//Fetch all products from DB by id
	List<ProductDetails> findAllByOrderByIdAsc ();

}
