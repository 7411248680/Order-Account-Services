package com.dxc.accountservice.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.dxc.accountservice.dto.AccountRequest;
import com.dxc.accountservice.dto.AccountResponce;
import com.dxc.accountservice.exception.AccountNumberException;
import com.dxc.accountservice.model.Account;
import com.dxc.accountservice.repository.AccountRepository;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Builder
public class AccountService implements AccountService1{
	
	private static final Logger LOG = LoggerFactory.getLogger(Account.class);

    private final AccountRepository accountRepository;
   
	public void createPayment(AccountRequest accountRequest) {
        Account account = Account.builder()
               // .paymentId(accountRequest.getPaymentId())
                .accountNumber(accountRequest.getAccountNumber())
                .balance(accountRequest.getBalance())
                .holderName(accountRequest.getHolderName())
                .build();

        accountRepository.save(account);
        LOG.debug("Product {} is saved", account.getId());
    }
	
    public List<AccountResponce>getAllPayments() {
        List<Account> order = accountRepository.findAll();

        return  order.stream().map(this::mapToOrderResponse).collect(Collectors.toList());
    }

    
    private AccountResponce mapToOrderResponse(Account account) {
        return AccountResponce.builder()
                .id(account.getId())
                .accountNumber(account.getAccountNumber())
                .balance(account.getBalance())
                .holderName(account.getHolderName())
                .build();
    }
    

    
    
//    public Optional<Account> getById(Long id)
//    {
//    	Optional<Account> findById = accountRepository.findById(id).orElseThrow(()->new AccountNumberException("Account Not Found"));
//    	return findById;
//    }
//    
    public Account updateAccount(Account account,Long id)
    {
    	Account account1 = accountRepository.findById(id).orElseThrow();
    	account1.setBalance(account.getBalance());
    	Account save = accountRepository.save(account1);
    	return save; 
    }

	@Override
	public Account findById(Long id) {
		Account account = accountRepository.findById(id).orElseThrow(()->new AccountNumberException("Account Not Found  "+id));
		return account;
	}

}
