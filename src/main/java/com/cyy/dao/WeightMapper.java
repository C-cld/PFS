package com.cyy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cyy.domain.Weight;

@Mapper
public interface WeightMapper 
{
	List<Weight> find();
	
	void add(Weight weight);
}
