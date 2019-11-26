package com.hcl.ecommerce.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hcl.ecommerce.entity.Product;
import com.hcl.ecommerce.exception.ProductNotAddedException;
import com.hcl.ecommerce.service.ProductService;

@RunWith(SpringJUnit4ClassRunner.class)
public class ProductControllerTest {
	@InjectMocks
	ProductController productController;

	@Mock
	ProductService productService;

	Product product = new Product();

	@Test
	public void testSaveProductPositive() throws ProductNotAddedException {
		String productDescription = "I-Phone is a smartphone made by Apple";
		String productName = "I-Phone";
		Mockito.when(productService.saveProduct(productName, productDescription))
				.thenReturn("Successfully added in product");
		int expected = productController.saveProduct(productName, productDescription).getStatusCodeValue();
		assertEquals(201, expected);
	}

	@Test
	public void testSaveProductNegative() throws ProductNotAddedException {
		String productDescription = "I-Phone is a smartphone made by Apple";
		String productName = "";
		Mockito.when(productService.saveProduct(productName, productDescription))
				.thenReturn("The details you have entered is null. Please check it");
		int expected = productController.saveProduct(productName, productDescription).getStatusCodeValue();
		assertEquals(204, expected);
	}
}
