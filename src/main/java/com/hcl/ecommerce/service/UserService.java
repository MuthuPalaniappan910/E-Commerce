package com.hcl.ecommerce.service;

import java.util.List;
import java.util.Optional;

import com.hcl.ecommerce.entity.MyOrder;
import com.hcl.ecommerce.entity.User;
import com.hcl.ecommerce.exception.OrderEmptyException;

public interface UserService {
	/**
	 * 
	 * @param userId
	 * @param userPassword
	 * @return
	 */

	Optional<User> getUserByIdAndPassword(Long userId, String userPassword);

	/**
	 * 
	 * @param userId
	 * @return
	 * @throws OrderEmptyException
	 */

	List<MyOrder> getOrderDetails(Long userId) throws OrderEmptyException;

}
