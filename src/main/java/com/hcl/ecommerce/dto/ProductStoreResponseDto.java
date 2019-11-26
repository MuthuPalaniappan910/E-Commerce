package com.hcl.ecommerce.dto;

import com.hcl.ecommerce.entity.ProductStore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductStoreResponseDto {
	private ProductStore productStore;
	private Double storeRating;
}
