package com.hcl.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.ecommerce.dto.StoreRequestDto;
import com.hcl.ecommerce.exception.ContactPersonInvalidException;
import com.hcl.ecommerce.exception.MobileNumberInvalidException;
import com.hcl.ecommerce.exception.StoreLocationInvalidException;
import com.hcl.ecommerce.exception.StoreNameInvalidException;
import com.hcl.ecommerce.service.StoreService;

import lombok.extern.slf4j.Slf4j;

/**
 * this controller will help admin to add the store details in store table
 * 
 * 
 * @author Muthu
 * 
 */

@RestController
@RequestMapping("/stores")
@Slf4j
public class StoreController {
	@Autowired
	StoreService storeService;

	/**
	 * @param StoreRequestDto that takes parameter
	 *                        storeName,contactPerson,contactNumber,storeLocation
	 * @return String which contains the message of success or failure for adding in
	 *         store table
	 * @throws StoreLocationInvalidException
	 * @throws MobileNumberInvalidException
	 * @throws ContactPersonInvalidException
	 * @throws StoreNameInvalidException
	 */

	@PostMapping("")
	public ResponseEntity<String> saveStore(@RequestBody StoreRequestDto storeRequestDto)
			throws StoreLocationInvalidException, MobileNumberInvalidException, ContactPersonInvalidException,
			StoreNameInvalidException {
		String store = storeService.saveStore(storeRequestDto);
		log.info("Successfully saving store details in db");
		if (store.equalsIgnoreCase("Successfully saved in store")) {
			return new ResponseEntity<>(store, HttpStatus.CREATED);
		}
		return new ResponseEntity<>(store, HttpStatus.NO_CONTENT);
	}

}
