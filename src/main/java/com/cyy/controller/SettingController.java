package com.cyy.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cyy.domain.Setting;
import com.cyy.service.SettingService;

@Controller
public class SettingController {
	@Autowired
	SettingService settingService;
	
	@RequestMapping(value = "/weight-setting")
	public ModelAndView setting() {
		List<Setting> settingList = settingService.find("weight", null);
		Map<String, String> settingMap = new HashMap<String, String>();
		for (Setting setting: settingList) {
			settingMap.put(setting.getKey(), setting.getValue());
		}
		ModelAndView mav = new ModelAndView("weight-setting");
		mav.addObject("weightSetting", settingMap);
		return mav;
	}
	
	@RequestMapping(value = "/save-weight-setting")
	public ModelAndView saveWeightSetting(@RequestParam(value = "weight-radio") String unit) {
		settingService.update(unit);
		ModelAndView mav=new ModelAndView("redirect:/weight-setting");
		return mav;
	}
}
