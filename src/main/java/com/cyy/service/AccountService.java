package com.cyy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyy.dao.AccountMapper;
import com.cyy.domain.Account;

@Service
public class AccountService {
	@Autowired
	private AccountMapper accountMapper;
	
	public List<Account> find(String keyword) {
		return accountMapper.find(keyword);
	}
}
