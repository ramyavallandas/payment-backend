package com.dbs.web.service;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbs.web.beans.Customer;
import com.dbs.web.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	public CustomerService() {
		System.out.println("Customer service");
	}

	public Customer findCustomerById(String id) {
		try {
			Optional<Customer> cust = this.customerRepository.findById(id);

			return cust.orElseThrow(() -> {

				return new EntityNotFoundException("Customer with " + id + " does not exist");
			});

		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public void save(Customer customer) {
		customerRepository.save(customer);
	}
	
	public boolean updateCustomer(Customer cust)
	{
		try {
			this.customerRepository.save(cust);
		}catch(IllegalArgumentException e )
		{
			return false;
		}
		return true;
	}

}
