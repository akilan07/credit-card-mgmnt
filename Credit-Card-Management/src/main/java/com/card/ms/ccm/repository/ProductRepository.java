package com.card.ms.ccm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.card.ms.ccm.entity.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

	Optional<Product> findByCreditScore(Integer creditScore);
	
}
