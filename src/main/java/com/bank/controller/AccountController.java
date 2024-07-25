package com.bank.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bank.Entity.Account;
import com.bank.repository.AccountRepository;
import com.bank.service.AccountService;

@RestController
@RequestMapping("/account")
public class AccountController {
	@Autowired
	 private AccountService service;
	@Autowired
	private AccountRepository repository;
	
	@PostMapping("/create")
	public ResponseEntity< Account> createaccount( @RequestBody Account account) {
		repository.save(account);
		return ResponseEntity.ok(account);

		
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Account> getAccountbyId(@PathVariable Long id){
		 
		 Account account=service.getAccountbyId(id);
		 ResponseEntity.status(HttpStatus.ACCEPTED);
		 return ResponseEntity.ok(account);
	}
	
	
	@GetMapping
	public List<Account> getAllAccount(){
		return service.getAllAccount();
	}
	
	@DeleteMapping("/{id}")
	public void deleteAccById(@PathVariable Long id) {
		service.deletAccById(id);
		
	}
	
	
	@PutMapping("/{id}/deposit")
	public ResponseEntity<Account> deposit(@PathVariable Long id,@RequestBody Map<String,Double>request){
		Double amount=request.get("amount");
		
		Account account=service.deposit(id, amount);
		return ResponseEntity.ok(account);
	}

	@PutMapping("/{id}/withdraw")
	public ResponseEntity<Account> withdraw(@PathVariable Long id,@RequestBody Map<String,Double>request){
		Double amount=request.get("amount");
		
		Account account=service.withdraw(id, amount);
		return ResponseEntity.ok(account);
	}
}
