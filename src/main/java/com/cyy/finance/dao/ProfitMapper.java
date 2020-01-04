package com.cyy.finance.dao;

import com.cyy.finance.domain.Profit;
import com.cyy.finance.model.ProfitSum;
import com.cyy.finance.model.ProfitSumPerDay;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProfitMapper {
    List<ProfitSumPerDay> getProfitSumPerDayList();

    List<ProfitSum> getProfitSumList();

    List<Profit> getProfitDetailList(Integer index, Integer pageSize, String productId);

    int getCount(String productId);

    boolean addProfitDetail(Profit profit);

    Float getTotalProfit();
}
