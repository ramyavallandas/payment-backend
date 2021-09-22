package com.dbs.web.models;

import lombok.Data;

@Data
public class TransactionPayload{
	private String customerid;
	private String currencycode;
	private String receiverbic;
	private String receiveraccountholdernumber;
	private String receiveraccountholdername;
	private String transfertypecode;
	private String messagecode;
	private Double currencyamount;
}