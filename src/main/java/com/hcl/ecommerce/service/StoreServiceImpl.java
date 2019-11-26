package com.hcl.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.ecommerce.dto.StoreRequestDto;
import com.hcl.ecommerce.entity.Store;
import com.hcl.ecommerce.exception.ContactPersonInvalidException;
import com.hcl.ecommerce.exception.MobileNumberInvalidException;
import com.hcl.ecommerce.exception.StoreLocationInvalidException;
import com.hcl.ecommerce.exception.StoreNameInvalidException;
import com.hcl.ecommerce.repository.StoreRepository;

/*
 * This enables the admin to add the store details in the store table
 */

@Service
public class StoreServiceImpl implements StoreService {
	@Autowired
	StoreRepository storeRepository;

	@Override
	public String saveStore(StoreRequestDto storeRequestDto) throws StoreLocationInvalidException,
			MobileNumberInvalidException, ContactPersonInvalidException, StoreNameInvalidException {
		Store store = new Store();
		String storeName = storeRequestDto.getStoreName();
		String contactPerson = storeRequestDto.getContactPerson();
		String contactNumber = storeRequestDto.getContactNumber();
		String storeLocation = storeRequestDto.getStoreLocation();
		if (!(storeName.equalsIgnoreCase("null")) && !storeName.trim().isEmpty()) {
			if (!(contactPerson.equalsIgnoreCase("null")) && !contactPerson.trim().isEmpty()) {
				if (contactNumber.length() == 10) {
					if (!(storeLocation.equalsIgnoreCase("null")) && !storeLocation.trim().isEmpty()) {
						store.setStoreName(storeName);
						store.setContactPerson(contactPerson);
						store.setContactNumber(contactNumber);
						store.setStoreLocation(storeLocation);
						storeRepository.save(store);
						return "Successfully saved in store";
					}
					throw new StoreLocationInvalidException("Please enter a valid store location");
				}
				throw new MobileNumberInvalidException("Please enter a valid number");
			}
			throw new ContactPersonInvalidException("Please enter a valid contact person");
		}
		throw new StoreNameInvalidException("Please enter a valid store name");
	}

}
