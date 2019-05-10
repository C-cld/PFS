package com.cyy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	@RequestMapping(value = "/")
	public ModelAndView indexPage() {
		try {
			return new ModelAndView("index");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
}
