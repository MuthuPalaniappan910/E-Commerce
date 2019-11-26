package com.hcl.ecommerce.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.ecommerce.entity.Product;
import com.hcl.ecommerce.exception.ProductNotAddedException;
import com.hcl.ecommerce.exception.ProductNotFoundException;
import com.hcl.ecommerce.repository.ProductRepository;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplTest {
	@InjectMocks
	ProductServiceImpl productServiceImpl;

	@Mock
	ProductRepository productRepository;

	Product product = new Product();

	@Test
	public void testAddProduct() throws ProductNotAddedException {
		String response = "Successfully added in product";
		String expected = productServiceImpl.saveProduct("I-Phone", "I-Phone is a smartphone made by Apple");
		assertEquals(response, expected);
	}

	@Test(expected = ProductNotAddedException.class)
	public void testProductNameNullException() throws ProductNotAddedException {
		String response = "The details you have entered is null. Please check it";
		String expected = productServiceImpl.saveProduct("null", "I-Phone is a smartphone made by Apple");
		assertEquals(response, expected);
	}

	@Test(expected = ProductNotAddedException.class)
	public void testProductNameEmptyException() throws ProductNotAddedException {
		String response = "The details you have entered is null. Please check it";
		String expected = productServiceImpl.saveProduct("", "I-Phone is a smartphone made by Apple");
		assertEquals(response, expected);
	}

	@Test(expected = ProductNotAddedException.class)
	public void testProductDescriptionNullException() throws ProductNotAddedException {
		String response = "The details you have entered is null. Please check it";
		String expected = productServiceImpl.saveProduct("I-Phone", "null");
		assertEquals(response, expected);
	}

	@Test(expected = ProductNotAddedException.class)
	public void testProductDescriptionEmptyException() throws ProductNotAddedException {
		String response = "The details you have entered is null. Please check it";
		String expected = productServiceImpl.saveProduct("I-Phone", "    ");
		assertEquals(response, expected);
	}

	@Test(expected = ProductNotAddedException.class)
	public void testProductDescriptionEmptyAndNameNullException() throws ProductNotAddedException {
		String response = "The details you have entered is null. Please check it";
		String expected = productServiceImpl.saveProduct("null", "    ");
		assertEquals(response, expected);
	}

	@Test(expected = ProductNotAddedException.class)
	public void testProductDescriptionNullAndNameEmptyException() throws ProductNotAddedException {
		String response = "The details you have entered is null. Please check it";
		String expected = productServiceImpl.saveProduct("", "Null");
		assertEquals(response, expected);
	}

	@Test(expected = ProductNotAddedException.class)
	public void testProductDescriptionNullAndNameNullException() throws ProductNotAddedException {
		String response = "The details you have entered is null. Please check it";
		String expected = productServiceImpl.saveProduct("null", "Null");
		assertEquals(response, expected);
	}

	@Test(expected = ProductNotAddedException.class)
	public void testProductDescriptionEmptyAndNameEmptyException() throws ProductNotAddedException {
		String response = "The details you have entered is null. Please check it";
		String expected = productServiceImpl.saveProduct("", "");
		assertEquals(response, expected);
	}

	@Test
	public void testGetProductByNameValid() throws ProductNotFoundException {
		List<Product> productDetails = new ArrayList<>();
		Product product2 = new Product();
		product2.setProductName("Laptop");
		productDetails.add(product2);
		Mockito.when(productRepository.findAll()).thenReturn(productDetails);
		List<Product> expectedProductList = productServiceImpl.getDetails("Lap");
		assertEquals(productDetails, expectedProductList);
	}

	@Test(expected = ProductNotFoundException.class)
	public void testGetProductByNameInValid() throws ProductNotFoundException {
		List<Product> productDetails = new ArrayList<>();
		Product product2 = new Product();
		product2.setProductName("Mobile");
		productDetails.add(product2);
		Mockito.when(productRepository.findAll()).thenReturn(productDetails);
		List<Product> expectedProductList = productServiceImpl.getDetails("Lap");
		assertEquals(productDetails, expectedProductList);
	}
}
