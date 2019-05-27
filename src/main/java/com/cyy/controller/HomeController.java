package com.cyy.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cyy.domain.Weight;
import com.cyy.service.CategoryService;
import com.cyy.service.WeightService;

@Controller
public class HomeController 
{
	@Autowired
	CategoryService categoryService;
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
			List<Weight> weightList = weightService.find();
			mav.addObject("weightList", weightList);
			return mav;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value = "/add")
	public ModelAndView add(@RequestParam(value = "date", required = true) String date, 
							@RequestParam(value = "amWeight", required = true, defaultValue = "0") String amWeight, 
							@RequestParam(value = "pmWeight", required = true, defaultValue = "0") String pmWeight) {
		try {
			weightService.add(date, amWeight, pmWeight);
		} catch (ParseException e) {
			// 日期转换异常
			e.printStackTrace();
		}
		ModelAndView mav=new ModelAndView("redirect:/index");
		return mav;
	}
}
