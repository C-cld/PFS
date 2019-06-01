package com.cyy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cyy.service.WeightService;

@Controller
public class HomeController 
{
	@Autowired
	WeightService weightService;
	
	
	@RequestMapping(value = "/")
	public ModelAndView sidebar() 
	{
		try 
		{
			ModelAndView mav=new ModelAndView("sidebar");
			return mav;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value = "/index")
	public ModelAndView index() 
	{
		try 
		{
			ModelAndView mav=new ModelAndView("index");
			return mav;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	
}
