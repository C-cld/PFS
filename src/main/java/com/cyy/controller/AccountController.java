package com.cyy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cyy.domain.Account;
import com.cyy.service.AccountService;

@Controller
public class AccountController {
	@Autowired
	AccountService accountService;

	@RequestMapping(value="/account")
	public ModelAndView account() {
		ModelAndView mav = new ModelAndView("account");
		List<Account> accountList = accountService.find("");
		mav.addObject("accountList", accountList);
		
		return mav;
	}
	
	@RequestMapping(value="/delete-account")
	@ResponseBody
	public String delete() {
		return "OK";
	}
}
