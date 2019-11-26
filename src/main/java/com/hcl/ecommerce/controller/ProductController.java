package com.hcl.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.ecommerce.exception.ProductNotAddedException;
import com.hcl.ecommerce.service.ProductService;

import lombok.extern.slf4j.Slf4j;

/**
 * this controller will help admin to add the product details in product table
 * 
 * 
 * @author Muthu
 * 
 */

@RestController
@RequestMapping("/products")
@Slf4j
public class ProductController {
	@Autowired
	ProductService productService;

	/**
	 * @param productName
	 * @param productDescription
	 * @return String which contains the message of success or failure for adding in
	 *         product table
	 * @throws ProductNotAddedException
	 */

	@PostMapping("")
	public ResponseEntity<String> saveProduct(@RequestParam String productName, @RequestParam String productDescription)
			throws ProductNotAddedException {
		String product = productService.saveProduct(productName, productDescription);
		log.info("Successfully saving product details in db");
		if (product.equalsIgnoreCase("Successfully added in product")) {
			return new ResponseEntity<>(product, HttpStatus.CREATED);
		}
		return new ResponseEntity<>(product, HttpStatus.NO_CONTENT);
	}

}
