package com.hcl.ecommerce.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hcl.ecommerce.entity.Product;
import com.hcl.ecommerce.exception.ProductNotFoundException;
import com.hcl.ecommerce.service.ProductService;

@RunWith(SpringJUnit4ClassRunner.class)
public class SearchControllerTest {
	@InjectMocks
	SearchController searchController;

	@Mock
	ProductService productService;

	Product product = new Product();
	List<Product> productList = new ArrayList<>();

	@Before
	public void setUp() {
		product.setProductDescription("Hand personal computer");
		product.setProductName("Laptop");
		product.setProductId(100L);
		productList.add(product);
	}

	@Test
	public void testGetProductValid() throws ProductNotFoundException {
		Mockito.when(productService.getDetails("Laptop")).thenReturn(productList);
		ResponseEntity<List<Product>> expectedList = searchController.getProductDetails("Laptop");
		assertEquals(HttpStatus.OK, expectedList.getStatusCode());
	}

	@Test
	public void testGetProductInValid() throws ProductNotFoundException {
		Mockito.when(productService.getDetails("Laptop")).thenReturn(productList);
		ResponseEntity<List<Product>> expectedList = searchController.getProductDetails("Mobile");
		assertEquals(HttpStatus.NOT_FOUND, expectedList.getStatusCode());
	}
}
