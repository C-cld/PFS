package com.cyy.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cyy.domain.FundInvestmentRecord;
import com.cyy.service.FinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class FinanceController {

    @Autowired
    FinanceService financeService;

    @RequestMapping(value = "/fund-investment-record")
    public ModelAndView fundInvestmentRecordPage() {
        ModelAndView mav = new ModelAndView("fund-investment-record");
        return mav;
    }

    @RequestMapping(value = "/search-record")
    @ResponseBody
    public String searchRecord() {
        List<FundInvestmentRecord> fundInvestmentRecordList = financeService.search();
        JSONArray array= JSONArray.parseArray(JSON.toJSONString(fundInvestmentRecordList));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg", "");
        jsonObject.put("code", 0);
        jsonObject.put("data", array);
        jsonObject.put("count", fundInvestmentRecordList.size());
        return jsonObject.toString();
    }

    @RequestMapping(value = "/fund-investment-record-add")
    public ModelAndView fundInvestmentRecordAddPage() {
        ModelAndView mav = new ModelAndView("fund-investment-record-add");
        return mav;
    }

}
