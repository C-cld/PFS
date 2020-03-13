package com.cyy.finance.dao;

import com.cyy.finance.domain.Profit;
import com.cyy.finance.model.CurrentMonthProfit;
import com.cyy.finance.model.MonthProfit;
import com.cyy.finance.model.SumProfitPerDay;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProfitMapper {
    List<SumProfitPerDay> getProfitPerDay(String startDate, String endDate, int category);
    List<CurrentMonthProfit> getCurrentMountProfit();
    List<MonthProfit> getMonthProfit(String startDate, String endDate, int category);
    void addProfit(Profit profit);
}


