package com.hcl.ecommerce.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hcl.ecommerce.entity.Product;
import com.hcl.ecommerce.exception.ProductNotAddedException;
import com.hcl.ecommerce.exception.ProductNotFoundException;
import com.hcl.ecommerce.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductRepository productRepository;

	@Override
	public String saveProduct(String productName, String productDescription) throws ProductNotAddedException {
		if (!(productName.equalsIgnoreCase("null")) && !productName.trim().isEmpty()
				&& !(productDescription.equalsIgnoreCase("null")) && !productDescription.trim().isEmpty()) {
			Product product = new Product();
			product.setProductName(productName);
			product.setProductDescription(productDescription);
			productRepository.save(product);
			return "Successfully added in product";
		}
		throw new ProductNotAddedException("The details you have entered is null. Please check it");
	}

	@Override
	public List<Product> getDetails(String productName) throws ProductNotFoundException {
		List<Product> productDetails = productRepository.findAll();
		productDetails = productDetails.stream()
				.filter(product -> product.getProductName().toLowerCase().startsWith(productName.toLowerCase()))
				.collect(Collectors.toList());
		if (productDetails.isEmpty()) {
			throw new ProductNotFoundException("No products found");
		}
		return productDetails;

	}

}
