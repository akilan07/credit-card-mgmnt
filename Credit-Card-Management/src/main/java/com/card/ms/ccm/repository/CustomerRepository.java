package com.card.ms.ccm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.card.ms.ccm.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Optional<Customer> findBycardNumber(String cardNumer);
}
