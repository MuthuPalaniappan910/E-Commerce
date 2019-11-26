package com.hcl.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.ecommerce.entity.MyOrder;
import com.hcl.ecommerce.entity.User;
import com.hcl.ecommerce.exception.OrderEmptyException;
import com.hcl.ecommerce.repository.MyOrderRepository;
import com.hcl.ecommerce.repository.UserRepository;

/*
 * First method enables the user to login based on the credentials entered
 * Second method lists the products the customer has purchased
 */

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;

	@Autowired
	MyOrderRepository myOrderRepository;

	@Override
	public Optional<User> getUserByIdAndPassword(Long userId, String userPassword) {
		return userRepository.findByUserIdAndUserPassword(userId, userPassword);
	}

	@Override
	public List<MyOrder> getOrderDetails(Long userId) throws OrderEmptyException {
		List<MyOrder> orderDetails = myOrderRepository.findAllByUserId(userId);
		if (orderDetails.isEmpty()) {
			throw new OrderEmptyException("There is no cart list for the user");
		}
		return orderDetails;
	}

}
