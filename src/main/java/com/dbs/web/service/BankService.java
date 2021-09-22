package com.dbs.web.service;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbs.web.beans.Bank;
import com.dbs.web.repository.BankRepository;

@Service
public class BankService {
	@Autowired
	private BankRepository bankRepository;
	
	public BankService() {
		System.out.println("bank service");
	}
	
	public Bank findBankName(String bic) 
	{
		try {
			Optional<Bank> bank = this.bankRepository.findById(bic);

			return bank.orElseThrow(()->{
	
				return new EntityNotFoundException("Bank with this code: "+ bic + "(does not exist)");
			});

		}catch(IllegalArgumentException e )
		{
			return null;
		}
	}
	
}
