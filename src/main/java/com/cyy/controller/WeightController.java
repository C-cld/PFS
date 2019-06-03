package com.cyy.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cyy.domain.Weight;
import com.cyy.service.SettingService;
import com.cyy.service.WeightService;

@Controller
public class WeightController {
	
	@Autowired
	WeightService weightService;
	@Autowired
	SettingService settingService;
	
	String unit = "";
	
	@RequestMapping(value = "/weight")
	public ModelAndView weight() 
	{
		try 
		{
			ModelAndView mav=new ModelAndView("weight");
			List<Weight> weightList = weightService.find();
			unit = settingService.find("weight","unit").get(0).getValue();
			mav.addObject("weightList", weightList);
			mav.addObject("unit", unit);
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
		double amWeightD = Double.valueOf(amWeight.toString());
		double pmWeightD = Double.valueOf(pmWeight.toString());
		try {
			if (unit.equals("0")) {
				weightService.add(date, amWeightD, pmWeightD);
			} else if (unit.equals("1")) {
				weightService.add(date, amWeightD/2, pmWeightD/2);
			}
			
		} catch (ParseException e) {
			// 日期转换异常
			e.printStackTrace();
		}
		ModelAndView mav=new ModelAndView("redirect:/weight");
		return mav;
	}
	
}
