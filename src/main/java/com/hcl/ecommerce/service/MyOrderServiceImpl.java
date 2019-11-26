package com.hcl.ecommerce.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.ecommerce.dto.MyOrderRequestDto;
import com.hcl.ecommerce.entity.MyOrder;
import com.hcl.ecommerce.entity.Product;
import com.hcl.ecommerce.entity.ProductStore;
import com.hcl.ecommerce.entity.User;
import com.hcl.ecommerce.repository.MyOrderRepository;
import com.hcl.ecommerce.repository.ProductRepository;
import com.hcl.ecommerce.repository.ProductStoreRepository;
import com.hcl.ecommerce.repository.UserRepository;

/*
 * This enables the customer to save the product in the customer product table and update the product store table
 */

@Service
@Transactional

public class MyOrderServiceImpl implements MyOrderService {
	@Autowired
	MyOrderRepository myOrderRepository;

	@Autowired
	ProductStoreRepository productStoreRepository;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	UserRepository userRepository;

	@Override
	public String saveCustomerProduct(MyOrderRequestDto myOrderRequestDto) {
		String productName = myOrderRequestDto.getProductName();
		String storeName = myOrderRequestDto.getStoreName();
		Long userId = myOrderRequestDto.getUserId();
		Product product = productRepository.findByProductName(productName);
		Long productId = product.getProductId();
		ProductStore productStore = productStoreRepository.findByProductIdAndStoreName(productId, storeName);
		User user = userRepository.findByUserId(userId);
		if (productStore != null && user != null) {
			MyOrder myOrder = new MyOrder();
			myOrder.setProductName(productName);
			myOrder.setStoreName(storeName);
			myOrder.setUserId(userId);
			Integer quantity = productStore.getProductQuantity();
			if(quantity != 0 ) {
			productStore.setProductQuantity(quantity - 1);
			productStoreRepository.save(productStore);
			myOrderRepository.save(myOrder);
			return "Successfully saved the product details";
			}
			else {
				return "There is no stock available in that store. Please check other stores";
			}
		}
		return null;
	}
}
