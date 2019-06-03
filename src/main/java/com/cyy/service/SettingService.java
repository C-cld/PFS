package com.cyy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyy.dao.SettingMapper;
import com.cyy.domain.Setting;

@Service
public class SettingService {
	@Autowired
	private SettingMapper settingMapper;
	
	public List<Setting> find(String type, String key) {
		Setting tempSetting = new Setting();
		tempSetting.setType(type);
		List<Setting> settingList = settingMapper.find(tempSetting);
		return settingList;
	}
	
	public void update(String unit) {
		Setting setting = new Setting();
		setting.setType("weight");
		setting.setKey("unit");
		setting.setValue(unit);
		settingMapper.update(setting);
	}
}
