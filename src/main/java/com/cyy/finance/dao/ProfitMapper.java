package com.cyy.finance.dao;

import com.cyy.finance.domain.Profit;
import com.cyy.finance.model.CurrentMonthProfit;
import com.cyy.finance.model.MonthProfit;
import com.cyy.finance.model.SumProfitPerDay;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProfitMapper {
    List<SumProfitPerDay> getProfitPerDay();
    List<CurrentMonthProfit> getCurrentMountProfit();
    List<MonthProfit> getMonthProfit();
    void addProfit(Profit profit);
}


