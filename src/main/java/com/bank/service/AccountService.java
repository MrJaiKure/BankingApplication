package com.bank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.Entity.Account;
import com.bank.repository.AccountRepository;

@Service
public class AccountService {
	@Autowired
	private AccountRepository accountRepository;
	
	
//	............ACCOUNT CREATED HERE.............
	Account createaccount(Account account) {
		return accountRepository.save(account);
	}
	

	
//	........GET ACCOUNT BY ID...........
	public Account getAccountbyId(Long id) {
		
		Account account = accountRepository
				.findById(id)
				.orElseThrow(()->new RuntimeException("ACCOUNT NOT EXISTS"));
		
		return accountRepository.findById(id).get();
		
	}
	
//	................GET ALL ACCOUNTS PRESENT IN THE BANK..................
	public List<Account> getAllAccount(){ 
		return accountRepository.findAll();
		}
	
	
//	.........DELETE ACCOUNT FROM USEING ID ...............
	public void deletAccById( Long id) {
		Account account = accountRepository
				.findById(id)
				.orElseThrow(()->new RuntimeException("ACCOUNT NOT EXISTS"));
		
		
		 accountRepository.deleteById(id);
		
		
	}
	
	public Account deposit(Long id,double amount) {

		Account account = accountRepository
				.findById(id)
				.orElseThrow(()->new RuntimeException("ACCOUNT NOT EXISTS"));

		
		double total =account.getBalance()+amount;
		account.setBalance(total);
		Account savedaccount=accountRepository.save(account);
		return savedaccount;
     }
	
	public Account withdraw(Long id,double amount) {

		Account account = accountRepository
				.findById(id)
				.orElseThrow(()->new RuntimeException("ACCOUNT NOT EXISTS"));
		
		if(account.getBalance()<amount) {
			throw new RuntimeException("Insufficeint Balance");
		}

		double total =account.getBalance()-amount;
		account.setBalance(total);
		Account savedaccount=accountRepository.save(account);
		return savedaccount;
     }
}
