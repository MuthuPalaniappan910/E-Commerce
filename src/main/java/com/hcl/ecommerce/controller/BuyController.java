package com.hcl.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.ecommerce.dto.MyOrderRequestDto;
import com.hcl.ecommerce.dto.ProductStoreResponseDto;
import com.hcl.ecommerce.exception.ProductNotFoundException;
import com.hcl.ecommerce.service.MyOrderService;
import com.hcl.ecommerce.service.ProductStoreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

/**
 * this controller will help customer to see and buy the product
 * 
 * 
 * @author Muthu
 * 
 */

@RestController
@RequestMapping("/buy")
@Slf4j
public class BuyController {
	@Autowired
	ProductStoreService productStoreService;

	@Autowired
	MyOrderService myOrderService;

	/**
	 * 
	 * @param productName
	 * @return
	 * @throws ProductNotFoundException
	 */

	@PostMapping("")
	public ResponseEntity<List<ProductStoreResponseDto>> getProductList(@RequestParam String productName)
			throws ProductNotFoundException {
		List<ProductStoreResponseDto> productList = productStoreService.getProductList(productName);
		log.info("Displaying the product from different stores");
		if (productList.isEmpty()) {
			return new ResponseEntity<>(productList, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(productList, HttpStatus.OK);
	}

	/**
	 * 
	 * @param myOrderRequestDto
	 * @return
	 */

	@PostMapping("/usercart")
	public ResponseEntity<String> addCart(@RequestBody MyOrderRequestDto myOrderRequestDto) {
		String response = myOrderService.saveCustomerProduct(myOrderRequestDto);
		log.info("Successfully saving product details");
		if(response.equals("Successfully saved the product details")) {
		return new ResponseEntity<>(response, HttpStatus.OK);
		}
		return new ResponseEntity<>(response,HttpStatus.NO_CONTENT);
	}

}
