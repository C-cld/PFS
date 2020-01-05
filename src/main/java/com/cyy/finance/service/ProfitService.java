package com.cyy.finance.service;

import com.cyy.finance.dao.ProfitMapper;
import com.cyy.finance.domain.Profit;
import com.cyy.finance.model.ProfitSumPerDayVO;
import com.cyy.finance.model.ProfitSumVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ProfitService {

    @Autowired
    ProfitMapper profitMapper;

    public List<ProfitSumPerDayVO> getProfitSumPerDayVOList() {
        List<ProfitSumPerDayVO> profitSumPerDayVOList = profitMapper.getProfitSumPerDayVOList();
        int size = profitSumPerDayVOList.size();
        float sum = 0;
        for (int i = 0; i < size; i ++) {
            ProfitSumPerDayVO profitSumPerDayVO = profitSumPerDayVOList.get(i);
            // 保留两位小数
            sum = (float)(Math.round((sum + profitSumPerDayVO.getSum())*100))/100;
            profitSumPerDayVO.setSum(sum);

        }
        return profitSumPerDayVOList;
    }

    public List<ProfitSumVO> getProfitSumVOList() {
        return profitMapper.getProfitSumVOList();
    }

    public List<Profit> getProfitDetailList(Integer page, Integer limit, String productId) {
        int index = (page - 1) * limit;
        return profitMapper.getProfitDetailList(index, limit, productId);
    }

    public int getCount(String productId) {
        return profitMapper.getCount(productId);
    }

    public boolean addProfitDetail(String productId, String createDate, Float value) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Profit profit = new Profit();
        profit.setId(UUID.randomUUID().toString().replace("-", "").toLowerCase());
        profit.setProductId(productId);
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
