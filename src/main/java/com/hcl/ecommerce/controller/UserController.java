package com.hcl.ecommerce.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.ecommerce.dto.UserResponseDto;
import com.hcl.ecommerce.entity.MyOrder;
import com.hcl.ecommerce.entity.User;
import com.hcl.ecommerce.exception.OrderEmptyException;
import com.hcl.ecommerce.service.UserService;

import lombok.extern.slf4j.Slf4j;

/**
 * this controller will return login details whether logged in credentials are
 * valid or not
 * 
 * @author Muthu
 * 
 */

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
	@Autowired
	UserService userService;

	/**
	 * 
	 * @param userId
	 * @param userPassword
	 * @return UserResponseDto which contains the display message and the status
	 *         code
	 */

	@PostMapping("")
	public ResponseEntity<UserResponseDto> getUserByIdAndPassword(@RequestParam Long userId,
			@RequestParam String userPassword) {
		log.info("Inside validating user method");
		UserResponseDto userResponseDto = new UserResponseDto();
		Optional<User> user = userService.getUserByIdAndPassword(userId, userPassword);
		if (user.isPresent()) {
			userResponseDto.setDisplayCode(200);
			userResponseDto.setDisplayMessage("You can search and buy using our site");
			return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
		}
		userResponseDto.setDisplayCode(404);
		userResponseDto.setDisplayMessage("Please check the credentials entered");
		return new ResponseEntity<>(userResponseDto, HttpStatus.NOT_FOUND);
	}

	/**
	 * 
	 * @param userId
	 * @return orderList
	 * @throws OrderEmptyException
	 */
	@PostMapping("/cart/{userId}")
	public ResponseEntity<List<MyOrder>> getUserOrders(@PathVariable("userId") Long userId) throws OrderEmptyException {
		List<MyOrder> orderDetailsList = userService.getOrderDetails(userId);
		if (orderDetailsList.isEmpty()) {
			return new ResponseEntity<>(orderDetailsList, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(orderDetailsList, HttpStatus.OK);
	}

}
