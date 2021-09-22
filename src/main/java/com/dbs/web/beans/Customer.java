package com.dbs.web.beans;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Customer {
	@Id
	private String customerid;
	@NotNull
	private String accountholdername;
	@NotNull
	private Boolean overdraftflag;
	@NotNull
	private Double clearbalance;
	
	
	
	
}
