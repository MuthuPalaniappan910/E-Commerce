package com.hcl.ecommerce.service;

import java.util.List;

import com.hcl.ecommerce.entity.Product;
import com.hcl.ecommerce.exception.ProductNotAddedException;
import com.hcl.ecommerce.exception.ProductNotFoundException;

public interface ProductService {
	/**
	 * 
	 * @param productName
	 * @param productDescription
	 * @return
	 * @throws ProductNotAddedException
	 */
	String saveProduct(String productName, String productDescription) throws ProductNotAddedException;

	/**
	 * 
	 * @param productName
	 * @return
	 * @throws ProductNotFoundException
	 */

	List<Product> getDetails(String productName) throws ProductNotFoundException;

}
