package com.dbs.web.beans;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
public class Transaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer transactionid;
	@ManyToOne
	@JoinColumn(name="customerid")
	private Customer customerid;
	@ManyToOne
	@JoinColumn(name="currenycode")
	private Currency currenycode;
	@ManyToOne
	@JoinColumn(name="receiverbic")
	private Bank receiverBic;
	private String receiveraccountholdernumber;
	private String receiveraccountholdername;
	@ManyToOne
	@JoinColumn(name="transfertypecode")
	private Transfertypes transfertypecode;
	@ManyToOne
	@JoinColumn(name="messagecode")
	private Message messagecode;
	private Double transferfees;
	private Double inramount;
	//private LocalDate transferdate;
	@Temporal(TemporalType.TIMESTAMP)
    @NotNull
    Date timestamp;

}
