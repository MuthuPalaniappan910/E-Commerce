package com.hcl.ecommerce.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "store")

@Getter
@Setter
@SequenceGenerator(name = "storesequence", initialValue = 100, allocationSize = 1)
public class Store {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "storesequence")
	private Long storeId;
	private String storeName;
	private String contactPerson;
	private String contactNumber;
	private String storeLocation;

}
