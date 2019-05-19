package com.cyy.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyy.dao.WeightMapper;
import com.cyy.domain.Weight;

@Service
public class WeightService 
{
	@Autowired
	private WeightMapper weightMapper;
	
	public List<Weight> find() 
	{
		return weightMapper.find();
	}
	
	public void add(String date, String amWeight, String pmWeight) throws ParseException
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd") ;
		Date datee = dateFormat.parse(date);
		Weight weight = new Weight();
		weight.setId(UUID.randomUUID().toString().replace("-", "").toLowerCase());
		weight.setDate(datee);
		weight.setAmWeight(Double.valueOf(amWeight.toString()));
		weight.setPmWeight(Double.valueOf(pmWeight.toString()));
		weightMapper.add(weight);
	}
}
