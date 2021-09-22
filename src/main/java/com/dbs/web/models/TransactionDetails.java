package com.dbs.web.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransactionDetails {
	private Double transferFee;
	private Double transferAmount;
	private Double totalInINR;
	private String transferTypeCode;
	
}
