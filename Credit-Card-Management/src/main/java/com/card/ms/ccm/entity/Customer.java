package com.card.ms.ccm.entity;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "customer")
@SecondaryTable(name = "product", pkJoinColumns = @PrimaryKeyJoinColumn(name = "product_id"))
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cust_id")
	private long custId;
	
	@Column(name = "cust_username")
	private String customerName;
	
	@Column(name = "card_no")
	private String cardNumber;
	
	@Embedded
	private Product product;

}
