package com.card.ms.ccm.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class IssuanceResponseDTO {
	
	private String cardNumber;
	private Error error;

}
