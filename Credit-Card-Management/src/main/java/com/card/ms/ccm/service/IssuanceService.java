package com.card.ms.ccm.service;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.card.ms.ccm.dto.Error;
import com.card.ms.ccm.dto.IssuanceRequestDTO;
import com.card.ms.ccm.dto.IssuanceResponseDTO;
import com.card.ms.ccm.entity.Customer;
import com.card.ms.ccm.entity.Product;
import com.card.ms.ccm.repository.CustomerRepository;
import com.card.ms.ccm.repository.ProductRepository;

@Service
public class IssuanceService {

	@Autowired
	Environment env;

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	CardGeneration cardGeneration;
	
	private Optional<Product> product;

	private Integer creditScore;
	
	private Customer customer;
	
	public IssuanceResponseDTO issueCard(IssuanceRequestDTO request) {
		if((creditScore = getCreditSore(request.getUniquieID())) != null) {
			product = productRepository.findByCreditScore(creditScore);
		}
		if(product.isPresent()) {
			customer = new Customer();
			customer.setCardNumber(cardGeneration.generateCardNumber());
			customer.setCustomerName(request.getCustomerName());
			customer.setProduct(product.get());
			customer = customerRepository.save(customer);
		} else {
			customer = null;
		}
		
		return prepareResponse();
	}
	
	private IssuanceResponseDTO prepareResponse() {
		IssuanceResponseDTO issuanceResponseDTO = new IssuanceResponseDTO();
		issuanceResponseDTO.setCardNumber(this.customer.getCardNumber());
		if(this.customer.getCardNumber() == null) {
			issuanceResponseDTO.setError(new Error("1111","Error"));
		}
		
		return issuanceResponseDTO;
	}

	@Cacheable("creditScore")
	private Integer getCreditSore(String uniquieID) {
		Integer creditScore = null;
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			HttpEntity<String> entity = new HttpEntity<String>(uniquieID, headers);
			
			creditScore = restTemplate.exchange( env.getProperty("creditscore.url"), HttpMethod.POST, entity, Integer.class).getBody();
			
		} catch (Exception e) {
			return null;
		}

		return creditScore;

	}

}
