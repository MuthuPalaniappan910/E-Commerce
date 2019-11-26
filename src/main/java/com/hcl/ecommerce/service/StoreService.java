package com.hcl.ecommerce.service;

import com.hcl.ecommerce.dto.StoreRequestDto;
import com.hcl.ecommerce.exception.ContactPersonInvalidException;
import com.hcl.ecommerce.exception.MobileNumberInvalidException;
import com.hcl.ecommerce.exception.StoreLocationInvalidException;
import com.hcl.ecommerce.exception.StoreNameInvalidException;

public interface StoreService {

	/**
	 * @param storeRequestDto
	 * @return
	 * @throws StoreLocationInvalidException
	 * @throws MobileNumberInvalidException
	 * @throws ContactPersonInvalidException
	 * @throws StoreNameInvalidException
	 */
	String saveStore(StoreRequestDto storeRequestDto) throws StoreLocationInvalidException,
			MobileNumberInvalidException, ContactPersonInvalidException, StoreNameInvalidException;

}
