package com.cyy.service;

import com.cyy.domain.FundInvestmentRecord;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FinanceService {
    public List<FundInvestmentRecord> search() {
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
}
