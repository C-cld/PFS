package com.cyy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cyy.domain.Setting;

@Mapper
public interface SettingMapper {
	List<Setting> find(Setting setting);
	void update(Setting setting);
}
