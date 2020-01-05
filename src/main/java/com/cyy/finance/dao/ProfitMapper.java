package com.cyy.finance.dao;

import com.cyy.finance.domain.Profit;
import com.cyy.finance.model.ProfitSumPerDayVO;
import com.cyy.finance.model.ProfitSumVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProfitMapper {
    List<ProfitSumPerDayVO> getProfitSumPerDayVOList();

    List<ProfitSumVO> getProfitSumVOList();

    List<Profit> getProfitDetailList(Integer index, Integer pageSize, String productId);

    int getCount(String productId);

    boolean addProfitDetail(Profit profit);

    Float getTotalProfit();
}
