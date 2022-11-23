package com.dxc.accountservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dxc.accountservice.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
	

}
