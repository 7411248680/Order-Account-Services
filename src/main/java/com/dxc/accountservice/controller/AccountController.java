package com.dxc.accountservice.controller;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.dxc.accountservice.dto.AccountRequest;
import com.dxc.accountservice.dto.AccountResponce;
import com.dxc.accountservice.model.Account;
import com.dxc.accountservice.service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/billing")
@RequiredArgsConstructor
public class AccountController {
	
	private static final String Order_Get_Link="http://localhost:8083/api/order";
	
	static RestTemplate restTemplate= new RestTemplate();
	
	@Autowired
	private AccountService accountService;
	
	ObjectMapper objectMapper = new ObjectMapper();
	
	@PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createBill(@RequestBody AccountRequest accountRequest) {
		accountService.createPayment(accountRequest);
            
    } 

    @GetMapping("/getbill")
    @ResponseStatus(HttpStatus.OK)
    public List<AccountResponce> getAllBills() {
        return accountService.getAllPayments();       
    }
    
    @GetMapping("/getOrders")
	private static List<Object> callGetAllOrdersAPLI() {
		
		Object[] result=	restTemplate.getForObject(Order_Get_Link, Object[].class);
					
			return Arrays.asList(result);
	}

    @GetMapping("/{id}")
    private ResponseEntity<Account> getAccountByAccountNumber(@PathVariable Long id)
    {
    	Account byAccountNumber = accountService.findById(id);
    	
    	return new ResponseEntity<Account>(byAccountNumber,HttpStatus.OK);
    	
    }
    
    @PutMapping("/update/{id}")
    private Account updateAccountBalance(@RequestBody Account account, @PathVariable Long id)
    {
    	Account updateAccount = accountService.updateAccount(account, id);
    	return updateAccount;
    }
    
}
