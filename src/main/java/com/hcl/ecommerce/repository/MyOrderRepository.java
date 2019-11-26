package com.hcl.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.ecommerce.entity.MyOrder;

@Repository
public interface MyOrderRepository extends JpaRepository<MyOrder, Long> {

	List<MyOrder> findAllByUserId(Long userId);

}
