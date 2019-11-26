package com.hcl.ecommerce.service;

import com.hcl.ecommerce.dto.MyOrderRequestDto;

public interface MyOrderService {
	/**
	 * 
	 * @param myOrderRequestDto
	 * @return
	 */
	String saveCustomerProduct(MyOrderRequestDto myOrderRequestDto);

}
