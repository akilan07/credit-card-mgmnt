package com.card.ms.ccm.service;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.card.ms.ccm.entity.Customer;
import com.card.ms.ccm.repository.CustomerRepository;

@Service
public class CardGeneration {
	
	@Autowired
	CustomerRepository customerRepository;
	
	private Optional<Customer> customer;

	public String generateCardNumber() {

		long smallest = 1000_0000_0000_0000L;
	    long biggest =  9999_9999_9999_9999L;

	    long random = ThreadLocalRandom.current().nextLong(smallest, biggest+1);
		
	    customer = customerRepository.findBycardNumber(String.valueOf(random));
	    
	    if(customer.isPresent()) {
	    	return generateCardNumber();
	    }
		return String.valueOf(random);
	}
	
}
