package com.cyy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cyy.domain.Category;

@Mapper
public interface CategoryMapper 
{
	List<Category> find();
}
