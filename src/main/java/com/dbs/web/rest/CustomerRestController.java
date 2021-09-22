package com.dbs.web.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dbs.web.beans.Customer;
import com.dbs.web.beans.Customeruser;
import com.dbs.web.beans.ResponsePage;
import com.dbs.web.repository.CustomerRepository;
import com.dbs.web.repository.CustomeruserRepository;
import com.dbs.web.service.CustomerService;

import lombok.Data;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/customer")
public class CustomerRestController {
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/{customerid}")
	public ResponseEntity<Object> findCustomerById(@PathVariable String customerid) 
	{
		try { 
			Customer cust = this.customerService.findCustomerById(customerid);
			return ResponseEntity.status(HttpStatus.OK)
					.body(cust);
					
			
		}catch (EntityNotFoundException e) {
			System.out.println("error");
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponsePage("failure", e.getMessage()));
		}
	}
	@Autowired
	CustomerRepository customerRepo;
	
	@Autowired
	CustomeruserRepository userRepo;
	
//	@PostMapping("/entry")
//	@ResponseBody
//	public CustomerRequest entry(@RequestBody CustomerRequest data){
//		
////		userRepo.save(new Customeruser("123184617"));
//		for(CustomerRequestItem item : data.getData()) {
//			Customer c=new Customer();
//			c.setAccountholdername(item.getAccountholdername());
//			c.setClearbalance(item.getClearbalance());
//			c.setOverdraftflag(item.getOverdraft());
//			c.setCustomerid(item.getCustomerid());
//			Customeruser cu=new Customeruser(item.getCustomerid());
//			cu.setCustomerid(c);
//			customerRepo.save(c);
//			userRepo.save(cu);
//		}
//		
//		return data;
//	}
// 
//	


}
//@Data
//class CustomerRequest{
//	private List<CustomerRequestItem> data;
//}
//@Data
//class CustomerRequestItem {
//	
//	private String customerid;
//	private String accountholdername;
//	private Float clearbalance;
//	private Boolean overdraft;}
	
	

