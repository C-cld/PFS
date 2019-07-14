package com.cyy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

	@RequestMapping(value="/search")
	public ModelAndView search(@RequestParam(value = "kname", required = true) String keyword) {
		ModelAndView mav = new ModelAndView("account");
		List<Account> accountList = accountService.find(keyword);
		mav.addObject("accountList", accountList);
		return mav;
	}

	@RequestMapping(value="/add-account")
	public ModelAndView add(@RequestParam(value="aname", required = true) String name,
							@RequestParam(value="ausername", required = true) String username,
							@RequestParam(value="apassword", required = true) String password,
							@RequestParam(value="aurl", required = true) String url) {
		accountService.add(name, username, password, url);
		ModelAndView mav=new ModelAndView("redirect:/account");
		return mav;
	}
	
	@RequestMapping(value="/delete-account")
	@ResponseBody
	public String delete() {
		return "OK";
	}
}
