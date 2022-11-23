package com.dxc.accountservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponce {
	
	private Long id;
	private Long accountNumber;
	private Float balance;
	private String holderName;

}
