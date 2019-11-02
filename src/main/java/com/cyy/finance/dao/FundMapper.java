package com.cyy.finance.dao;

import com.cyy.finance.domain.Fund;
import com.cyy.finance.domain.FundInvestmentRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FundMapper {
    List<Fund> searchFund();
    List<FundInvestmentRecord> searchInvestmentRecord(@Param("fundCode") String fundCode);
    void insertFund(Fund fund);
}
