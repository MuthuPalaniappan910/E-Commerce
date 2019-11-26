package com.hcl.ecommerce.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.ecommerce.dto.StoreRequestDto;
import com.hcl.ecommerce.entity.Store;
import com.hcl.ecommerce.exception.ContactPersonInvalidException;
import com.hcl.ecommerce.exception.MobileNumberInvalidException;
import com.hcl.ecommerce.exception.StoreLocationInvalidException;
import com.hcl.ecommerce.exception.StoreNameInvalidException;
import com.hcl.ecommerce.repository.StoreRepository;

@RunWith(MockitoJUnitRunner.class)
public class StoreServiceImplTest {
	@InjectMocks
	StoreServiceImpl storeServiceImpl;

	@Mock
	StoreRepository storeRepository;

	Store store = new Store();
	StoreRequestDto storeRequestDto = new StoreRequestDto();
	String contactNumber = "9786785647";
	String contactPerson = "Sujith";
	String storeLocation = "Bangalore";
	String storeName = "Sujith Stores";

	@Test
	public void testSaveStoreSuccess() throws StoreLocationInvalidException, MobileNumberInvalidException,
			ContactPersonInvalidException, StoreNameInvalidException {
		storeRequestDto.setContactNumber(contactNumber);
		storeRequestDto.setContactPerson(contactPerson);
		storeRequestDto.setStoreLocation(storeLocation);
		storeRequestDto.setStoreName(storeName);
		String response = "Successfully saved in store";
		String expected = storeServiceImpl.saveStore(storeRequestDto);
		assertEquals(response, expected);
	}

	@Test(expected = StoreNameInvalidException.class)
	public void testSaveStoreNameNull() throws StoreLocationInvalidException, MobileNumberInvalidException,
			ContactPersonInvalidException, StoreNameInvalidException {
		storeRequestDto.setStoreName("Null");
		String response = "Please enter a valid store name";
		String expected = storeServiceImpl.saveStore(storeRequestDto);
		assertEquals(response, expected);
	}

	@Test(expected = StoreNameInvalidException.class)
	public void testSaveStoreNameEmpty() throws StoreLocationInvalidException, MobileNumberInvalidException,
			ContactPersonInvalidException, StoreNameInvalidException {
		storeRequestDto.setStoreName("");
		String response = "Please enter a valid store name";
		String expected = storeServiceImpl.saveStore(storeRequestDto);
		assertEquals(response, expected);
	}

	@Test(expected = ContactPersonInvalidException.class)
	public void testSaveContactPersonNull() throws StoreLocationInvalidException, MobileNumberInvalidException,
			ContactPersonInvalidException, StoreNameInvalidException {
		storeRequestDto.setStoreName("Ajay Store");
		storeRequestDto.setContactPerson("Null");
		String response = "Please enter a valid contact person";
		String expected = storeServiceImpl.saveStore(storeRequestDto);
		assertEquals(response, expected);
	}

	@Test(expected = ContactPersonInvalidException.class)
	public void testSaveContactPersonEmpty() throws StoreLocationInvalidException, MobileNumberInvalidException,
			ContactPersonInvalidException, StoreNameInvalidException {
		storeRequestDto.setStoreName("Ajay Store");
		storeRequestDto.setContactPerson("");
		String response = "Please enter a valid contact person";
		String expected = storeServiceImpl.saveStore(storeRequestDto);
		assertEquals(response, expected);
	}

	@Test(expected = MobileNumberInvalidException.class)
	public void testSaveMobileNumberInvalid() throws StoreLocationInvalidException, MobileNumberInvalidException,
			ContactPersonInvalidException, StoreNameInvalidException {
		storeRequestDto.setStoreName("Ajay Store");
		storeRequestDto.setContactPerson("Ajay");
		storeRequestDto.setContactNumber("98765");
		String response = "Please enter a valid number";
		String expected = storeServiceImpl.saveStore(storeRequestDto);
		assertEquals(response, expected);
	}

	@Test(expected = StoreLocationInvalidException.class)
	public void testSaveStoreLocationNull() throws StoreLocationInvalidException, MobileNumberInvalidException,
			ContactPersonInvalidException, StoreNameInvalidException {
		storeRequestDto.setStoreName("Ajay Store");
		storeRequestDto.setContactPerson("Ajay");
		storeRequestDto.setContactNumber("9876543210");
		storeRequestDto.setStoreLocation("Null");
		String response = "Please enter a valid store location";
		String expected = storeServiceImpl.saveStore(storeRequestDto);
		assertEquals(response, expected);
	}

	@Test(expected = StoreLocationInvalidException.class)
	public void testSaveStoreLocationEmpty() throws StoreLocationInvalidException, MobileNumberInvalidException,
			ContactPersonInvalidException, StoreNameInvalidException {
		storeRequestDto.setStoreName("Ajay Store");
		storeRequestDto.setContactPerson("Ajay");
		storeRequestDto.setContactNumber("9876543210");
		storeRequestDto.setStoreLocation("");
		String response = "Please enter a valid store location";
		String expected = storeServiceImpl.saveStore(storeRequestDto);
		assertEquals(response, expected);
	}
}
