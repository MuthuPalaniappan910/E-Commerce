package com.hcl.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoreRequestDto {
	private String storeName;
	private String contactPerson;
	private String contactNumber;
	private String storeLocation;
}
