package com.card.ms.ccm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Error {
	
	private String errorCode;
	private String errorResponse;

}
