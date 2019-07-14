package com.cyy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cyy.domain.Account;

@Mapper
public interface AccountMapper {
	List<Account> find(String keyword);
	void add(Account account);
}
