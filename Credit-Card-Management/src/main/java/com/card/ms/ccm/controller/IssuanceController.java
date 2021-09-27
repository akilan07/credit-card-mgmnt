package com.card.ms.ccm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.card.ms.ccm.dto.IssuanceRequestDTO;
import com.card.ms.ccm.dto.IssuanceResponseDTO;
import com.card.ms.ccm.service.IssuanceService;

@RestController
public class IssuanceController {
	
	@Autowired
	IssuanceService IssuanceService;

	@PostMapping(value="/issue-card", consumes = "application/json", produces = "application/json")
	public IssuanceResponseDTO issueCard(@RequestBody IssuanceRequestDTO request) {

		IssuanceResponseDTO issuanceResponseDTO = IssuanceService.issueCard(request);
		
		return issuanceResponseDTO;
	}

}
