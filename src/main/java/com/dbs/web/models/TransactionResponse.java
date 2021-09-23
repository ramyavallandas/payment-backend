package com.dbs.web.models;

import java.time.LocalDate;
import java.util.Date;

import com.dbs.web.beans.Customer;

import lombok.Data;
@Data
public class TransactionResponse {
	private Customer sender;
	private TransactionDetails transaction;
	private Date date;
	private String receiverBIC;
	private String receiverAccountNumber;
	private String receiverAccountName;
	private String messageCode;
	public TransactionResponse(TransactionRequest request, Customer customer, Double currencyInINR, Double transferFee, Double totalAmount) {
		this.sender=customer;
		this.transaction=new TransactionDetails(transferFee,currencyInINR,totalAmount,request.getPayload().getTransfertypecode());
		this.receiverAccountName=request.getPayload().getReceiveraccountholdername();
		this.receiverAccountNumber=request.getPayload().getReceiveraccountholdernumber();
		this.messageCode=request.getPayload().getMessagecode();
		
		
		
	}
	
	
	
}

