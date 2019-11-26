package com.hcl.ecommerce.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hcl.ecommerce.dto.StoreRequestDto;
import com.hcl.ecommerce.exception.ContactPersonInvalidException;
import com.hcl.ecommerce.exception.MobileNumberInvalidException;
import com.hcl.ecommerce.exception.StoreLocationInvalidException;
import com.hcl.ecommerce.exception.StoreNameInvalidException;
import com.hcl.ecommerce.service.StoreService;

@RunWith(SpringJUnit4ClassRunner.class)
public class StoreControllerTest {
	@InjectMocks
	StoreController storeController;

	@Mock
	StoreService storeService;

	StoreRequestDto storeRequestDto = new StoreRequestDto();
	StoreRequestDto storeRequestDto2 = new StoreRequestDto();
	String contactNumber = "9786785647";
	String contactPerson = "Sujith";
	String storeLocation = "Bangalore";
	String storeName = "Sujith Stores";
	String contactNumber2 = "9786785647";
	String contactPerson2 = "";
	String storeLocation2 = "Bangalore";

	@Test
	public void testSaveStorePositive() throws StoreLocationInvalidException, MobileNumberInvalidException,
			ContactPersonInvalidException, StoreNameInvalidException {
		storeRequestDto.setContactNumber(contactNumber);
		storeRequestDto.setContactPerson(contactPerson);
		storeRequestDto.setStoreLocation(storeLocation);
		storeRequestDto.setStoreName(storeName);
		Mockito.when(storeService.saveStore(storeRequestDto)).thenReturn("Successfully saved in store");
		int expected = storeController.saveStore(storeRequestDto).getStatusCodeValue();
		assertEquals(201, expected);
	}

	@Test
	public void testSaveStoreNegative() throws StoreLocationInvalidException, MobileNumberInvalidException,
			ContactPersonInvalidException, StoreNameInvalidException {
		storeRequestDto2.setContactNumber(contactNumber2);
		storeRequestDto2.setContactPerson(contactPerson2);
		storeRequestDto2.setStoreLocation(storeLocation2);
		storeRequestDto2.setStoreName(storeName);
		Mockito.when(storeService.saveStore(storeRequestDto2)).thenReturn("Please enter a valid contact person");
		int expected = storeController.saveStore(storeRequestDto2).getStatusCodeValue();
		assertEquals(204, expected);
	}
}
