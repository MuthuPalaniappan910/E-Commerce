package com.hcl.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.ecommerce.entity.Product;
import com.hcl.ecommerce.exception.ProductNotFoundException;
import com.hcl.ecommerce.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/search")
@Slf4j
public class SearchController {
	@Autowired
	ProductService productService;

	/**
	 * 
	 * @param productName
	 * @return list containing the productDetails
	 * @throws ProductNotFoundException
	 */

	@PostMapping("")
	public ResponseEntity<List<Product>> getProductDetails(@RequestParam String productName)
			throws ProductNotFoundException {
		List<Product> productDetails = productService.getDetails(productName);
		log.info("Displaying product details from product table");
		if (productDetails.isEmpty()) {
			return new ResponseEntity<>(productDetails, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(productDetails, HttpStatus.OK);
	}
}
