package com.hcl.ecommerce.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hcl.ecommerce.dto.UserResponseDto;
import com.hcl.ecommerce.entity.MyOrder;
import com.hcl.ecommerce.entity.User;
import com.hcl.ecommerce.exception.OrderEmptyException;
import com.hcl.ecommerce.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserControllerTest {

	ResponseEntity<UserResponseDto> userResponseDto = null;

	@Before
	public void setup() {
		userResponseDto = new ResponseEntity<UserResponseDto>(HttpStatus.FORBIDDEN);
	}

	@InjectMocks
	UserController userController;

	@Mock
	UserService userService;

	MyOrder order = new MyOrder();
	List<MyOrder> orderList = new ArrayList<>();
	List<MyOrder> orderList2 = new ArrayList<>();

	@Before
	public void before() {
		order.setOrderId(1L);
		order.setProductName("Laptop");
		order.setStoreName("Arun Stores");
		order.setUserId(51820777L);
		orderList.add(order);
	}

	@Test
	public void testUserByIdAndPasswordPositive() {
		Long userId = 100L;
		String userPassword = "muthu910";
		Integer response = 200;
		Mockito.when(userService.getUserByIdAndPassword(userId, userPassword)).thenReturn(Optional.of(new User()));
		userResponseDto = userController.getUserByIdAndPassword(100L, "muthu910");
		assertEquals(response, userResponseDto.getBody().getDisplayCode());
	}

	@Test
	public void testUserByIdAndPasswordNegative() {
		Long userId = 100L;
		String userPassword = "palani910";
		Integer response = 404;
		Mockito.when(userService.getUserByIdAndPassword(userId, userPassword)).thenReturn(Optional.of(new User()));
		userResponseDto = userController.getUserByIdAndPassword(100L, "muthu910");
		assertEquals(response, userResponseDto.getBody().getDisplayCode());
	}

	@Test
	public void testGetOrderDetailsPositive() throws OrderEmptyException {
		Long userId = 51820777L;
		Mockito.when(userService.getOrderDetails(userId)).thenReturn(orderList);
		ResponseEntity<List<MyOrder>> responseList = userController.getUserOrders(userId);
		assertEquals(HttpStatus.OK, responseList.getStatusCode());
	}

	@Test
	public void testGetOrderDetailsNegative() throws OrderEmptyException {
		Long userId = 51820778L;
		Mockito.when(userService.getOrderDetails(userId)).thenReturn(orderList2);
		ResponseEntity<List<MyOrder>> responseList = userController.getUserOrders(userId);
		assertEquals(HttpStatus.NO_CONTENT, responseList.getStatusCode());
	}
}
