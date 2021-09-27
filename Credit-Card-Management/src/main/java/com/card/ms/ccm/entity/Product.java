package com.card.ms.ccm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private long productID;
	
	@Column(name = "product_name")
	private String productName;
	
	@Column(name = "creditScore")
	private String creditScore;
	
	@Column(name="limit")
	private int limit;

}
