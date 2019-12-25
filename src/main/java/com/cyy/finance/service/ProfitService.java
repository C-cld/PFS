package com.cyy.finance.service;

import com.cyy.finance.dao.ProfitMapper;
import com.cyy.finance.domain.Profit;
import com.cyy.finance.dto.ProfitSum;
import com.cyy.finance.dto.ProfitSumPerDay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

@Service
public class ProfitService {

    @Autowired
    ProfitMapper profitMapper;

    public List<Profit> getProfitSumPerDayList() {
        return profitMapper.getProfitSumPerDayList();
    }

    public List<ProfitSum> getProfitSumList() {
        return profitMapper.getProfitSumList();
    }

    public List<Profit> getProfitDetailList(Integer page, Integer limit, String fundId) {
        int index = (page - 1) * limit;
        return profitMapper.getProfitDetailList(index, limit, fundId);
    }

    public int getCount(String fundId) {
        return profitMapper.getCount(fundId);
    }

    public boolean addProfitDetail(String fundId, String createDate, Float value) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Profit profit = new Profit();
        profit.setId(UUID.randomUUID().toString().replace("-", "").toLowerCase());
        profit.setFundId(fundId);
        profit.setProfit(value);
        profit.setCreateDate(simpleDateFormat.parse(createDate));
        return profitMapper.addProfitDetail(profit);
    }
}
