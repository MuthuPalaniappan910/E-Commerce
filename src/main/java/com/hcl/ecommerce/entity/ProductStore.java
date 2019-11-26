package com.hcl.ecommerce.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "productstore")

@Getter
@Setter
public class ProductStore {
	@Id
	private Long productStoreId;
	private Long productId;
	private String storeName;
	private Double productprice;
	private Integer productQuantity;

}
