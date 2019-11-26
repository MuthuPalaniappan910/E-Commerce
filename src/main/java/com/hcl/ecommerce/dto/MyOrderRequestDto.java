package com.hcl.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyOrderRequestDto {
	private Long userId;
	private String storeName;
	private String productName;
}
