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
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hcl.ecommerce.dto.MyOrderRequestDto;
import com.hcl.ecommerce.dto.ProductStoreResponseDto;
import com.hcl.ecommerce.entity.Product;
import com.hcl.ecommerce.entity.ProductStore;
import com.hcl.ecommerce.entity.Review;
import com.hcl.ecommerce.entity.Store;
import com.hcl.ecommerce.exception.ProductNotFoundException;
import com.hcl.ecommerce.service.MyOrderService;
import com.hcl.ecommerce.service.ProductStoreService;

@RunWith(SpringJUnit4ClassRunner.class)
public class BuyControllerTest {
	@InjectMocks
	BuyController buyController;

	@Mock
	ProductStoreService productStoreService;

	@Mock
	MyOrderService myOrderService;

	List<ProductStore> productStoreList = null;
	List<ProductStoreResponseDto> responseList = null;
	List<Store> storeList = null;
	List<Review> reviewList = null;
	Product product = null;
	ProductStore productStore = null;
	List<ProductStoreResponseDto> responseList1 = null;
	ProductStoreResponseDto productStoreResponseDto = new ProductStoreResponseDto();

	@Before
	public void before() {
		product = new Product();
		product.setProductName("Mobile");
		product.setProductId(1L);

		productStoreList = new ArrayList<>();
		productStore = new ProductStore();
		productStore.setProductId(1L);
		productStore.setProductprice(17000.0);
		productStore.setProductQuantity(5);
		productStore.setStoreName("Ajay Stores");
		productStoreList.add(productStore);

		storeList = new ArrayList<>();
		Store store = new Store();
		store.setStoreId(100L);
		store.setContactNumber("9876543210");
		store.setContactPerson("Arun");
		store.setStoreLocation("Chennai");
		store.setStoreName("Ajay Stores");
		storeList.add(store);

		reviewList = new ArrayList<>();
		Review review = new Review();
		review.setRating(2.2);
		review.setReviewId(1L);
		review.setStoreId(100L);
		reviewList.add(review);

		responseList = new ArrayList<>();
		productStoreResponseDto.setProductStore(productStore);
		productStoreResponseDto.setStoreRating(2.2);
		responseList.add(productStoreResponseDto);

	}

	@Test
	public void testGetProductListPositive() throws ProductNotFoundException {
		Mockito.when(productStoreService.getProductList("Mobile")).thenReturn(responseList);
		int expected = buyController.getProductList("Mobile").getStatusCodeValue();
		assertEquals(200, expected);
	}

	@Test
	public void testGetProductListNegative() throws ProductNotFoundException {
		Mockito.when(productStoreService.getProductList("Mobile")).thenReturn(responseList);
		int expected = buyController.getProductList("MobilePhone").getStatusCodeValue();
		assertEquals(204, expected);
	}

	@Test
	public void testaddCartSuccess() {
		MyOrderRequestDto myOrderRequestDto = new MyOrderRequestDto();
		myOrderRequestDto.setProductName("Mobile");
		myOrderRequestDto.setUserId(1L);
		myOrderRequestDto.setStoreName("Ajay Stores");
		String message = "Successfully saved the product details";
		Mockito.when(myOrderService.saveCustomerProduct(myOrderRequestDto)).thenReturn(message);
		int expected = buyController.addCart(myOrderRequestDto).getStatusCodeValue();
		assertEquals(200, expected);
	}
	
	@Test
	public void testaddCartFailure() {
		MyOrderRequestDto myOrderRequestDto = new MyOrderRequestDto();
		myOrderRequestDto.setProductName("Mobile");
		myOrderRequestDto.setUserId(1L);
		myOrderRequestDto.setStoreName("Ajay Stores");
		String message = "There is no stock available in that store. Please check other stores";
		Mockito.when(myOrderService.saveCustomerProduct(myOrderRequestDto)).thenReturn(message);
		int expected=buyController.addCart(myOrderRequestDto).getStatusCodeValue();
		assertEquals(204, expected);
	}
}
