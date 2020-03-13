package com.cyy.finance.service;

import com.cyy.finance.dao.ProfitMapper;
import com.cyy.finance.domain.Profit;
import com.cyy.finance.model.CurrentMonthProfit;
import com.cyy.finance.model.MonthProfit;
import com.cyy.finance.model.SumProfitPerDay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfitService {

    @Autowired
    ProfitMapper profitMapper;

    public List<SumProfitPerDay> getCurveData(String startDate, String endDate, int category) {
        List<SumProfitPerDay> sumProfitPerDayList = profitMapper.getProfitPerDay(startDate, endDate, category);
        //int size = sumProfitPerDayList.size();
        float sum = 0;
        for (SumProfitPerDay s : sumProfitPerDayList) {
            // 保留两位小数
            // sum = (float)(Math.round((sum + s.getSum())*100))/100;
            sum = sum + s.getSum();
            s.setSum(sum);
        }
        return sumProfitPerDayList;
    }

    public List<CurrentMonthProfit> getCurrentMountProfit() {
        return profitMapper.getCurrentMountProfit();
    }

    public List<MonthProfit> getMonthProfit(String startDate, String endDate, Integer category) {
        return profitMapper.getMonthProfit(startDate, endDate, category);
    }

    public void addProfit(Profit profit) {
        profitMapper.addProfit(profit);
    }
}
