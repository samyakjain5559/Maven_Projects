package com.products.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.products.dao.ProductDetails;
import com.products.dao.ProductDetailsRepository;
import com.products.models.ProductCartDetails;


@Service
@Transactional
public class ProductDetailsService {

	Logger logger = LoggerFactory.getLogger(ProductDetailsService.class);
	
	@Autowired
	ProductDetailsRepository productDetailsRepository;
	
	@Autowired
	List<ProductCartDetails> productCartDetailList;
	

	public List<ProductDetails> getProductDetails() {
		List<ProductDetails> productDetailsDAO = new  ArrayList<ProductDetails>();

		try {
			//Fetch all products from DB
			productDetailsDAO = productDetailsRepository.findAllByOrderByIdAsc();
		}catch(Exception e) {
			logger.error("ProductDetailsService.getProductDetails() - Failed to get products from DB");
			logger.error(e.getMessage());
		}
		logger.info("ProductDetailsService.getProductDetails() - Successfully retrieved products from DB");

		return productDetailsDAO;
	}
	
	public String addProducttoCart(int id) {
		
		try {
			//Condition to check if product already exists in cart or not
			if(null!=this.productCartDetailList.stream().filter(p->id==p.getId()).findAny().orElse(null)) {
				logger.info("ProductDetailsService.addProducttoCart() - Product Exists in cart");
				this.productCartDetailList.stream().filter(p->id==p.getId()).forEach(p -> p.setQuantity(p.getQuantity()+1));
			}else {
				logger.info("ProductDetailsService.addProducttoCart() - Product doesnot exists in cart");

				ProductCartDetails productCartDetails = new ProductCartDetails();
				List<ProductDetails> productDetailsList = getProductDetails();
				Optional<ProductDetails> productDetails = productDetailsList.stream().filter(p->id==p.getId()).findFirst();
				productCartDetails.setDescription(productDetails.get().getDescription());
				productCartDetails.setId(productDetails.get().getId());
				productCartDetails.setImage(productDetails.get().getImage());
				productCartDetails.setName(productDetails.get().getName());
				productCartDetails.setQuantity(1);
				productCartDetails.setUnitPrice(productDetails.get().getUnitPrice());
				
				//Add new product in the cart in session
				this.productCartDetailList.add(productCartDetails);
			}
		}catch(Exception e) {
			logger.error("ProductDetailsService.addProducttoCart() - Failed to add/update product");
			logger.error(e.getMessage());
		}
		logger.info("ProductDetailsService.addProducttoCart() - Successfully added/updated product");

		return "success";
	}
	
	public List<ProductCartDetails> getProductfromCart() {
		try {
			//Return product details from cart in session
			return productCartDetailList;
		}catch(Exception e) {
			logger.error("ProductDetailsService.getProductfromCart() - Error ocured while retrieving products in cart");
			return null;
		}finally {
			logger.info("ProductDetailsService.getProductfromCart() - Successfully retrieved products in cart");
		}
	}

	public String removeProductfromCart(int id) {
		try {
			//Condition to check if only 1 piece of this product exists in cart
			if(this.productCartDetailList.stream().filter(p->id==p.getId()).findFirst().get().getQuantity()==1) {
				//Remove the product from cart
				this.productCartDetailList.remove(this.productCartDetailList.stream().filter(p->id==p.getId()).findFirst().get());
			}else {
				//Decrease the quantity of the given product from cart in session
				this.productCartDetailList.stream().filter(p->id==p.getId()).forEach(p -> p.setQuantity(p.getQuantity()-1));
			}
		}catch(Exception e) {
			logger.error("ProductDetailsService.removeProductfromCart() - Unable to remove product from cart");
			logger.error(e.getMessage());
		}
		logger.info("ProductDetailsService.removeProductfromCart() - Successfully removed product");
		return "success";
	}

	public String removeAllProductsfromCart() {
		try {
			//Remove all products in cart in session
			this.productCartDetailList.clear();
		}catch(Exception e) {
			logger.error("ProductDetailsService.removeAllProductsfromCart() - Unable to remove all products from cart");
			logger.error(e.getMessage());
		}
		logger.info("ProductDetailsService.removeAllProductsfromCart() - Successfully emptied cart");
		return "success";
	}

	
}
	
