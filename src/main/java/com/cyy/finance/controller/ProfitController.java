package com.cyy.finance.controller;

import com.cyy.finance.domain.Category;
import com.cyy.finance.domain.Profit;
import com.cyy.finance.model.CurrentMonthProfit;
import com.cyy.finance.model.MonthProfit;
import com.cyy.finance.model.SumProfitPerDay;
import com.cyy.finance.service.ProfitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
public class ProfitController {

    @Autowired
    ProfitService profitService;

    /**
     * 收益页
     * @return ModelAndView
     */
    @RequestMapping(value = "/profit")
    public ModelAndView profitPage() {
        ModelAndView modelAndView = new ModelAndView("finance/profit");
        return modelAndView;
    }

    @RequestMapping(value = "/get-current-month-profit")
    @ResponseBody
    public List<CurrentMonthProfit> getCurrentMountProfit() {
        return profitService.getCurrentMountProfit();
    }

    @RequestMapping(value = "/get-sum-profit-per-day")
    @ResponseBody
    public List<SumProfitPerDay> getSumProfitPerDay(String startDate, String endDate, int category) {
        return profitService.getSumProfitPerDay(startDate, endDate, category);
    }

    @RequestMapping(value = "/get-month-profit")
    @ResponseBody
    public List<MonthProfit> getMonthProfit(String startDate, String endDate, Integer category) {
        return profitService.getMonthProfit(startDate, endDate, category);
    }

    @RequestMapping(value = "/add-profit")
    @ResponseBody
    public void addProfit(@RequestBody Profit profit) {
        profitService.addProfit(profit);
    }

}
