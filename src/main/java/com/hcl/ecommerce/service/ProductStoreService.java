package com.hcl.ecommerce.service;

import java.util.List;

import com.hcl.ecommerce.dto.ProductStoreResponseDto;
import com.hcl.ecommerce.exception.ProductNotFoundException;

public interface ProductStoreService {
	/**
	 * 
	 * @param productName
	 * @return
	 * @throws ProductNotFoundException
	 */

	List<ProductStoreResponseDto> getProductList(String productName) throws ProductNotFoundException;

}
