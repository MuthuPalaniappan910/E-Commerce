package com.hcl.ecommerce.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

import lombok.extern.slf4j.Slf4j;

/*
 * This implementation enables the customer to view the product from the available stores along with their ratings
 */

@Service
@Slf4j
public class ProductStoreServiceImpl implements ProductStoreService {
	@Autowired
	ProductStoreRepository productStoreRepository;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	ReviewRepository reviewRepository;

	@Autowired
	StoreRepository storeRepository;

	@Override
	public List<ProductStoreResponseDto> getProductList(String productName) throws ProductNotFoundException {
		List<ProductStoreResponseDto> responseList = new ArrayList<>();
		Product product = productRepository.findByProductName(productName);
		if (product != null) {
			Long productId = product.getProductId();
			List<ProductStore> productStoreList = productStoreRepository.findAllByProductId(productId);
			log.info("No of List={}", productStoreList.size());
			for (ProductStore productStore : productStoreList) {
				List<Store> storeDetails = storeRepository.findByStoreName(productStore.getStoreName());
				log.info("StoreName={}", productStore.getStoreName());
				ProductStoreResponseDto productStoreResponseDto = new ProductStoreResponseDto();
				productStoreResponseDto.setProductStore(productStore);
				for (Store storeDetail : storeDetails) {
					List<Review> reviews = reviewRepository.findAllByStoreId(storeDetail.getStoreId());
					log.info("StroreId={}", storeDetail.getStoreId());
					for (Review review : reviews) {
						productStoreResponseDto.setStoreRating(review.getRating());
					}
					responseList.add(productStoreResponseDto);
				}
			}
		} else {
			throw new ProductNotFoundException("There is no such product available");
		}
		return responseList;
	}
}
