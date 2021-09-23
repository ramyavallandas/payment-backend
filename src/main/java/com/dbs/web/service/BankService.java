package com.dbs.web.service;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbs.web.beans.Bank;
import com.dbs.web.repository.BankRepository;

/**
 * The type Bank service.
 */
@Service
@AllArgsConstructor
public class BankService {

	private BankRepository bankRepository;


	/**
	 * Instantiates a new Bank service.
	 */
	public BankService() {
		System.out.println("bank service");
	}

	/**
	 * Find bank name bank.
	 *
	 * @param bic This is some BIC u don't want to know
	 * @return Edo okati pampista baa enduku niku? avasarama? code open chesukuko antha doola unte
	 */
/*
	@return Bank by given string
	 */
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
