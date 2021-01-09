package com.products.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.products.dao.ProductDetails;
import com.products.models.ProductCartDetails;
import com.products.services.ProductDetailsService;

@RestController
@RequestMapping("/products")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ShoppingApiController {
	
	Logger logger = LoggerFactory.getLogger(ShoppingApiController.class);

	@Autowired   // bean are object created by spring container of classes
	// autowire connect these beans/object of all class
	public ProductDetailsService productDetailsService;
	
	/*
	 * Used to fetch product details from the DB
	 * returns - List<ProductDetails>
	 */
	@CrossOrigin
	@GetMapping(value="getProductDetails")
	public List<ProductDetails> getProductDetails() {
		logger.info("ShoppingApiController.getProductDetails() - Begins");
		return productDetailsService.getProductDetails();
	}
	
	/*
	 * Used to add products to cart in session
	 * returns - String
	 */
	@CrossOrigin
	@GetMapping(value="addProductToCart/{id}")
	public String addProducttoCart(@PathVariable("id") int id) {
		logger.info("ShoppingApiController.addProducttoCart() - Begins");
		return productDetailsService.addProducttoCart(id);
	}
	
	/*
	 * Used to get products from cart in session
	 * returns - List<ProductCartDetails>
	 */
	@CrossOrigin
	@GetMapping(value="getProductfromCart")
	public List<ProductCartDetails> getProductfromCart() {
		logger.info("ShoppingApiController.getProductfromCart() - Begins");
		return productDetailsService.getProductfromCart();
	}
	
	/*
	 * Used to remove product from cart in session
	 * returns - String
	 */
	@CrossOrigin
	@GetMapping(value="removeProductfromCart/{id}")
	public String removeProductfromCart(@PathVariable("id") int id) {
		logger.info("ShoppingApiController.removeProductfromCart() - Begins");	
		return productDetailsService.removeProductfromCart(id);
	}
	
	/*
	 * Used to remove all products from cart in session
	 * returns - String
	 */
	@CrossOrigin
	@GetMapping(value="removeAllProductsfromCart")
	public String removeAllProductsfromCart() {
		logger.info("ShoppingApiController.removeAllProductsfromCart() - Begins");	
		return productDetailsService.removeAllProductsfromCart();
	}

}
