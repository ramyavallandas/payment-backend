package com.dbs.web.beans;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Logger {
	@Id
	private Integer loggerid;
	@ManyToOne
	@JoinColumn(name="customerid")
	private Customer customerid;
	@ManyToOne
	@JoinColumn(name="userid")
	private Customeruser userid;
	private Integer employeeid;
	private String screenname;
	private String action;
	private String ipaddress;

}
