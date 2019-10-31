package com.cyy.finance.service;

import com.cyy.finance.dao.FundMapper;
import com.cyy.finance.domain.Fund;
import com.cyy.finance.domain.FundInvestmentRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FundService {

    @Autowired
    FundMapper fundMapper;

    public List<FundInvestmentRecord> searchInvestmentRecord() {
        // TODO: 2019/10/30 各种过滤条件
        List<FundInvestmentRecord> fundInvestmentRecordList = new ArrayList<>();
        FundInvestmentRecord fundInvestmentRecord = new FundInvestmentRecord();
        fundInvestmentRecord.setId("1");
        fundInvestmentRecord.setProductId("001021");
        fundInvestmentRecord.setShare(45.12F);
        fundInvestmentRecord.setNet(1.26F);
        fundInvestmentRecord.setConfirm(20000.00F);
        fundInvestmentRecord.setServiceFee(3.15F);
        fundInvestmentRecord.setCreateDate(new Date());
        fundInvestmentRecordList.add(fundInvestmentRecord);
        return fundInvestmentRecordList;
    }

    public List<Fund> searchFund() {
        return fundMapper.searchFund();
    }

    public void insertFund(String fundCode) {
        Fund fund = new Fund();
        fund.setId("324234");
        fund.setName("234234234234");
        fund.setType(1);
        fundMapper.insertFund(fund);
    }
}
