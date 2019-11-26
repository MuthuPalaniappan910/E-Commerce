package com.hcl.ecommerce.service;

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
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.ecommerce.entity.MyOrder;
import com.hcl.ecommerce.entity.User;
import com.hcl.ecommerce.exception.OrderEmptyException;
import com.hcl.ecommerce.repository.UserRepository;
import com.hcl.ecommerce.repository.MyOrderRepository;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {
	@InjectMocks
	UserServiceImpl userService;

	@Mock
	UserRepository userRepository;

	@Mock
	MyOrderRepository myOrderRepository;

	User user = new User();
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
	public void testUserByIdAndPassword() {
		Long userId = 100L;
		String userPassword = "muthu910";
		Optional<User> users = Optional.of(user);
		Mockito.when(userRepository.findByUserIdAndUserPassword(userId, userPassword)).thenReturn(users);
		Optional<User> expected = userService.getUserByIdAndPassword(userId, userPassword);
		assertEquals(true, expected.isPresent());
	}

	@Test
	public void testGetOrderDetailsPositive() throws OrderEmptyException {
		Long userId = 51820777L;
		Mockito.when(myOrderRepository.findAllByUserId(userId)).thenReturn(orderList);
		List<MyOrder> responseList = userService.getOrderDetails(userId);
		assertEquals(responseList, orderList);
	}

	@Test(expected = OrderEmptyException.class)
	public void testGetOrderDetailsNegative() throws OrderEmptyException {
		Long userId = 51820778L;
		Mockito.when(myOrderRepository.findAllByUserId(userId)).thenReturn(orderList2);
		List<MyOrder> responseList = userService.getOrderDetails(userId);
		assertEquals(responseList, orderList);
	}
}
