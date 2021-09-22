package com.dbs.web.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dbs.web.beans.Bank;
import com.dbs.web.beans.Customer;
import com.dbs.web.beans.Message;
import com.dbs.web.beans.ResponsePage;
import com.dbs.web.beans.Transaction;
import com.dbs.web.models.TransactionPayload;
import com.dbs.web.models.TransactionRequest;
import com.dbs.web.models.TransactionResponse;
import com.dbs.web.repository.BankRepository;
import com.dbs.web.repository.CurrencyRepository;
import com.dbs.web.repository.CustomerRepository;
import com.dbs.web.repository.MessageRepository;
import com.dbs.web.repository.TransactionRepository;
import com.dbs.web.repository.TransfertypesRepository;
import com.dbs.web.utils.Permutations;


@Service

public class TransactionService {
	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private CurrencyRepository currencyRepo;

	@Autowired
	private BankRepository bankRepo;
	
	@Autowired 
	private MessageRepository messageRepo;
	
	
	
	
	
	
	
	
	
	

	@Transactional
	public ResponseEntity<Object> processTransaction(TransactionRequest request) {
		
		TransactionPayload payload = request.getPayload();
		Customer customer = customerService.findCustomerById((request.getPayload().getCustomerid()));
		
		
		// TODO black list check	filter of sender
		// Resource re = new ClassPathResource("classpath:datalist.txt");
		try {
			File f1 = ResourceUtils.getFile("classpath:datalist.txt");
	        FileReader fr = new FileReader(f1);  //Creation of File Reader object
	        BufferedReader br = new BufferedReader(fr); //Creation of BufferedReader object
	        String s;
	        String[] words=payload.getReceiveraccountholdername().split(" ");
	        Permutations putil=new Permutations(Arrays.asList(words));
	        List<String> permutations=putil.getAllCombinations();
	        String pattern="("+String.join("|", permutations)+")";
	        System.out.println(pattern);
	        Pattern p = Pattern.compile(pattern,Pattern.CASE_INSENSITIVE);
	        while ((s = br.readLine()) != null) {
	            Matcher m = p.matcher(s);
	            if (m.find()) {
	            	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponsePage("Red Alert",
	    					"The Account Holder name is found in SDNList... This will be reported to higher authority.... DANGER!!! DANGER!!!!"));
	            }
	        }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
		}
		    
		
		//end code
		
		Double currencyInINR = currencyRepo.findById(payload.getCurrencycode()).get().getConversionrate().doubleValue()
				* payload.getCurrencyamount();
		Double transferFee=0.0025 * currencyInINR;
		Double totalAmount=currencyInINR+transferFee;
		if (customer.getClearbalance() < totalAmount && !customer.getOverdraftflag()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponsePage("Transaction Failed",
					"Insuffient Balance in you Account."));
		}
		if(customer.getAccountholdername().toUpperCase().contains("HDFC BANK")&&payload.getTransfertypecode()!="O") {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponsePage("Transaction Failed:Invalid TransferType",
					"Account Holder is an HDFC! Transfer Code must be Transfer to Own account (HDFC)"));
		}
		customer.setClearbalance(customer.getClearbalance()-Math.abs(totalAmount));
		customerService.save(customer);
		Transaction savedTrans=saveTransactionItem(customer, totalAmount, payload,transferFee );
//		Transaction t1 = new Transaction(request,customer,currencyInINR,transferFee,totalAmount);
//		insertTransaction(t1);
		
		return ResponseEntity.status(HttpStatus.OK)
				.body(savedTrans);
		
	}


	
	private boolean insertTransaction(Transaction trans) {
		// TODO Auto-generated method stub
		try {
			this.transactionRepository.save(trans);	
		}catch(IllegalArgumentException e) {
		  return false;
	  }
	return true;
		
	}



	public List<Transaction> getTranscation(){
		List<Transaction> transaction = new ArrayList<Transaction>();
		this.transactionRepository.findAll().forEach(trans->transaction.add(trans));
		return transaction;
		
	}
	
	
	
	public Transaction getTransactionById(Integer transactionId) throws EntityNotFoundException
	{
		
		Optional<Transaction> trans = transactionRepository.findById(transactionId);
		if(trans==null) {
			throw new EntityNotFoundException("Transaction not found");
		}
		return trans.get();
	}



	public long getCount() {
		// TODO Auto-generated method stub
		long count = transactionRepository.count();
		
		return count;
	}



	public  boolean addTransaction(Transaction transaction) throws Exception {
		// TODO Auto-generated method stub
		
		try {
			if(this.transactionRepository.findById(transaction.getTransactionid()).isPresent())
			{
				return false;
			}
			this.transactionRepository.save(transaction);
		}
		catch(IllegalArgumentException e)
		{
			throw new IllegalArgumentException("The id is null");
		}
		catch(Exception e)
		{
			throw new Exception(e);
		}
		return true;
	}

	@Autowired
	TransfertypesRepository ttypes;


	private Transaction saveTransactionItem(Customer customer, Double totalAmount,
	            TransactionPayload payload, Double transferFee) {
			Transaction transactionItem = new Transaction();
			transactionItem.setCustomerid(customer);
			transactionItem.setInramount(totalAmount);
			transactionItem.setCurrenycode(currencyRepo.findById(payload.getCurrencycode()).get());
			transactionItem.setTransfertypecode(ttypes.findById(payload.getTransfertypecode()).get());
			transactionItem.setTimestamp(new Date());
			transactionItem.setTransferfees(transferFee);
			transactionItem.setReceiverBic(bankRepo.findById(payload.getReceiverbic()).get());
			transactionItem.setMessagecode(messageRepo.findById(payload.getMessagecode()).get());
			transactionItem.setReceiveraccountholdernumber(payload.getReceiveraccountholdernumber());
			transactionItem.setReceiveraccountholdername(payload.getReceiveraccountholdername());
			
			return transactionRepository.save(transactionItem);
	}
		



	
}
