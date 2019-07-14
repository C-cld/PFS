package com.cyy.service;

import java.util.List;
import java.util.UUID;

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

	public void add(String name, String username, String password, String url) {
		Account account = new Account();
		account.setId(UUID.randomUUID().toString().replace("-", "").toLowerCase());
		account.setName(name);
		account.setUsername(username);
		account.setPassword(password);
		account.setUrl(url);
		accountMapper.add(account);
	}
}
