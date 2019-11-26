package com.hcl.ecommerce.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.ecommerce.dto.MyOrderRequestDto;
import com.hcl.ecommerce.entity.Product;
import com.hcl.ecommerce.entity.ProductStore;
import com.hcl.ecommerce.entity.User;
import com.hcl.ecommerce.repository.MyOrderRepository;
import com.hcl.ecommerce.repository.ProductRepository;
import com.hcl.ecommerce.repository.ProductStoreRepository;
import com.hcl.ecommerce.repository.UserRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class MyOrderServiceImplTest {

	@InjectMocks
	MyOrderServiceImpl myOrderService;

	@Mock
	ProductStoreRepository productStoreRepository;

	@Mock
	ProductRepository productRepository;

	@Mock
	UserRepository userRepository;

	@Mock
	MyOrderRepository myOrderRepository;

	MyOrderRequestDto myOrderRequestDto = new MyOrderRequestDto();;
	Product product = new Product();
	ProductStore productStore = new ProductStore();
	User user = new User();

	@Test
	public void testCartSuccess() {
		myOrderRequestDto.setProductName("Laptop");
		myOrderRequestDto.setStoreName("Ajay Stores");
		myOrderRequestDto.setUserId(1L);
		product.setProductId(100L);
		product.setProductName("Laptop");
		productStore.setProductId(100L);
		productStore.setProductprice(18000.00);
		productStore.setProductQuantity(5);
		productStore.setProductStoreId(200L);
		productStore.setStoreName("Ajay Stores");
		user.setUserId(1L);
		Mockito.when(productRepository.findByProductName("Laptop")).thenReturn(product);
		Mockito.when(productStoreRepository.findByProductIdAndStoreName(100L, "Ajay Stores")).thenReturn(productStore);
		Mockito.when(userRepository.findByUserId(1L)).thenReturn(user);
		String expected = "Successfully saved the product details";
		String response = myOrderService.saveCustomerProduct(myOrderRequestDto);
		assertEquals(response, expected);
	}

	@Test
	public void testCartProductNull() {
		myOrderRequestDto.setProductName("Laptop");
		myOrderRequestDto.setStoreName("Ajay Stores");
		myOrderRequestDto.setUserId(1L);
		product.setProductId(200L);
		product.setProductName("mobile");
		productStore.setProductId(100L);
		productStore.setProductprice(18000.00);
		productStore.setProductQuantity(5);
		productStore.setProductStoreId(200L);
		productStore.setStoreName("Ajay Stores");
		user.setUserId(1L);
		Mockito.when(productRepository.findByProductName("Laptop")).thenReturn(product);
		Mockito.when(productStoreRepository.findByProductIdAndStoreName(200L, "laptop")).thenReturn(productStore);
		Mockito.when(userRepository.findByUserId(1L)).thenReturn(user);
		String response = myOrderService.saveCustomerProduct(myOrderRequestDto);
		assertEquals(null,response);
	}

	@Test
	public void testCartUserNull() {
		myOrderRequestDto.setProductName("Laptop");
		myOrderRequestDto.setStoreName("Ajay Stores");
		myOrderRequestDto.setUserId(1L);
		product.setProductId(100L);
		product.setProductName("Laptop");
		productStore.setProductId(100L);
		productStore.setProductprice(18000.00);
		productStore.setProductQuantity(5);
		productStore.setProductStoreId(200L);
		productStore.setStoreName("Ajay Stores");
		user.setUserId(1L);
		Mockito.when(productRepository.findByProductName("Laptop")).thenReturn(product);
		Mockito.when(productStoreRepository.findByProductIdAndStoreName(100L, "Ajay Stores")).thenReturn(productStore);
		Mockito.when(userRepository.findByUserId(2L)).thenReturn(user);
		String response = myOrderService.saveCustomerProduct(myOrderRequestDto);
		assertEquals(null,response);
	}
	
	@Test
	public void testQuantityNull() {
		myOrderRequestDto.setProductName("Laptop");
		myOrderRequestDto.setStoreName("Ajay Stores");
		myOrderRequestDto.setUserId(1L);
		product.setProductId(100L);
		product.setProductName("Laptop");
		productStore.setProductId(100L);
		productStore.setProductprice(18000.00);
		productStore.setProductQuantity(0);
		productStore.setProductStoreId(200L);
		productStore.setStoreName("Ajay Stores");
		user.setUserId(1L);
		Mockito.when(productRepository.findByProductName("Laptop")).thenReturn(product);
		Mockito.when(productStoreRepository.findByProductIdAndStoreName(100L, "Ajay Stores")).thenReturn(productStore);
		Mockito.when(userRepository.findByUserId(1L)).thenReturn(user);
		String expected = "There is no stock available in that store. Please check other stores";
		String response = myOrderService.saveCustomerProduct(myOrderRequestDto);
		assertEquals(response, expected);
	}
}
