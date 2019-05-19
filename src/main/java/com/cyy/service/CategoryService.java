package com.cyy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyy.dao.CategoryMapper;
import com.cyy.domain.Category;

@Service
public class CategoryService 
{
	@Autowired
	private CategoryMapper categoryMapper;
	
	public List<Category> find() {
		return categoryMapper.find();
	}
}
