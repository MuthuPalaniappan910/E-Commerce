package com.hcl.ecommerce.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.ecommerce.dto.ProductStoreResponseDto;
import com.hcl.ecommerce.entity.Product;
import com.hcl.ecommerce.entity.ProductStore;
import com.hcl.ecommerce.entity.Review;
import com.hcl.ecommerce.entity.Store;
import com.hcl.ecommerce.exception.ProductNotFoundException;
import com.hcl.ecommerce.repository.ProductRepository;
import com.hcl.ecommerce.repository.ProductStoreRepository;
import com.hcl.ecommerce.repository.ReviewRepository;
import com.hcl.ecommerce.repository.StoreRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ProductStoreServiceImplTest {
	@InjectMocks
	ProductStoreServiceImpl productStoreServiceImpl;

	@Mock
	ProductStoreRepository productStoreRepository;

	@Mock
	ReviewRepository reviewRepository;

	@Mock
	StoreRepository storeRepository;

	@Mock
	ProductRepository productRepository;

	List<ProductStore> productStoreList = null;
	List<ProductStoreResponseDto> responseList = null;
	List<Store> storeList = null;
	List<Review> reviewList = null;
	Product product = null;
	ProductStoreResponseDto productStoreResponseDto = new ProductStoreResponseDto();

	@Before
	public void before() {
		product = new Product();
		product.setProductName("Mobile");
		product.setProductId(1L);

		productStoreList = new ArrayList<>();
		ProductStore productStore = new ProductStore();
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

	@Test(expected = ProductNotFoundException.class)
	public void testGetProductEmptyException() throws ProductNotFoundException {
		String message = "There is no such product available";
		Mockito.when(productRepository.findByProductName("Mobile")).thenReturn(new Product());
		List<ProductStoreResponseDto> expected = productStoreServiceImpl.getProductList("Laptop");
		assertEquals(expected, message);
	}

	@Test
	public void testGetProductValid() throws ProductNotFoundException {
		Mockito.when(productRepository.findByProductName("Mobile")).thenReturn(product);
		Mockito.when(productStoreRepository.findAllByProductId(1L)).thenReturn(productStoreList);
		Mockito.when(storeRepository.findByStoreName("Ajay Stores")).thenReturn(storeList);
		Mockito.when(reviewRepository.findAllByStoreId(100L)).thenReturn(reviewList);
		List<ProductStoreResponseDto> expected = productStoreServiceImpl.getProductList("Mobile");
		assertEquals(expected.size(), responseList.size());

	}
}
