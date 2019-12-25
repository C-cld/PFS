package com.cyy.finance.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cyy.finance.domain.Profit;
import com.cyy.finance.dto.ProfitSum;
import com.cyy.finance.dto.ProfitSumPerDay;
import com.cyy.finance.service.ProfitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ProfitController {
    @Autowired
    ProfitService profitService;

    @RequestMapping(value = "/profit")
    public ModelAndView profitPage() {
        ModelAndView modelAndView = new ModelAndView("/finance/profit");
        return modelAndView;
    }

    @RequestMapping(value = "/profit-sum-per-day")
    @ResponseBody
    List<Profit> getProfitSumPerDayList() {
        return profitService.getProfitSumPerDayList();
    }

    @RequestMapping(value = "/my-fund")
    @ResponseBody
    public String myFund() {
        List<ProfitSum> profitSumList = profitService.getProfitSumList();
        JSONArray jsonArray = (JSONArray) JSONArray.toJSON(profitSumList);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg", "");
        jsonObject.put("code", 0);
        jsonObject.put("data", jsonArray);
        jsonObject.put("count", profitSumList.size());
        return jsonObject.toString();
    }

    @RequestMapping(value = "/profit-detail")
    public ModelAndView profitDetailPage(String fundId) {
        ModelAndView modelAndView = new ModelAndView("/finance/profit-detail");
        modelAndView.addObject("fundId", fundId);
        return modelAndView;
    }

    @RequestMapping(value = "/profit-detail-list")
    @ResponseBody
    public String getProfitDetailList(Integer page, Integer limit, String fundId) {
        List<Profit> profitDetailList = profitService.getProfitDetailList(page, limit, fundId);
        int count = profitService.getCount(fundId);
        JSONArray jsonArray = (JSONArray) JSONArray.toJSON(profitDetailList);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg", "");
        jsonObject.put("code", 0);
        jsonObject.put("data", jsonArray);
        jsonObject.put("count", count);
        return jsonObject.toString();
    }

    @RequestMapping(value = "/add-profit-detail")
    @ResponseBody
    public boolean addProfitDetail(String fundId, String createDate, Float profit) {
        try {
            return profitService.addProfitDetail(fundId, createDate, profit);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }


}