package com.cyy.finance.service;

import com.cyy.finance.dao.ProfitMapper;
import com.cyy.finance.domain.Profit;
import com.cyy.finance.model.ProfitSum;
import com.cyy.finance.model.ProfitSumPerDay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ProfitService {

    @Autowired
    ProfitMapper profitMapper;

    public List<ProfitSumPerDay> getProfitSumPerDayList() {
        List<ProfitSumPerDay> profitSumPerDayList = profitMapper.getProfitSumPerDayList();
        int size = profitSumPerDayList.size();
        float sum = 0;
        for (int i = 0; i < size; i ++) {
            ProfitSumPerDay profitSumPerDay = profitSumPerDayList.get(i);
            // 保留两位小数
            sum = (float)(Math.round((sum + profitSumPerDay.getSum())*100))/100;
            profitSumPerDay.setSum(sum);

        }
        return profitSumPerDayList;
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

    public Float getTotalProfit() {
        return profitMapper.getTotalProfit();
    }

    public Map<Integer, Float> getPercentage() {
        Map<Integer, Float> map = new HashMap<>();
        map.put(0, 30000.01F);
        map.put(1, 20000.01F);
        map.put(2, 10000.01F);
        return map;
    }
}
