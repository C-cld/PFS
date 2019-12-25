package com.cyy.finance.dao;

import com.cyy.finance.domain.Profit;
import com.cyy.finance.dto.ProfitSum;
import com.cyy.finance.dto.ProfitSumPerDay;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProfitMapper {
    List<Profit> getProfitSumPerDayList();

    List<ProfitSum> getProfitSumList();

    List<Profit> getProfitDetailList(Integer index, Integer pageSize, String fundId);

    int getCount(String fundId);

    boolean addProfitDetail(Profit profit);
}