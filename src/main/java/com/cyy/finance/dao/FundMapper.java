package com.cyy.finance.dao;

import com.cyy.finance.domain.Fund;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FundMapper {
    List<Fund> searchFund();
    void insertFund(Fund fund);
}
