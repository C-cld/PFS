package com.cyy.finance.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cyy.finance.domain.FundInvestmentRecord;
import com.cyy.finance.service.FundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class FundController {

    @Autowired
    FundService fundService;

    /**
     * 基金定投记录页
     * @return
     */
    @RequestMapping(value = "/fund-investment-record")
    public ModelAndView fundInvestmentRecordPage() {
        ModelAndView mav = new ModelAndView("/finance/fund-investment-record");
        return mav;
    }

    /**
     * 记录添加页
     * @return
     */
    @RequestMapping(value = "/fund-investment-record-add")
    public ModelAndView fundInvestmentRecordAddPage() {
        ModelAndView mav = new ModelAndView("/finance/fund-investment-record-add");
        return mav;
    }

    /**
     * 基金定投记录页表格数据
     * @return
     */
    @RequestMapping(value = "/search-record")
    @ResponseBody
    public String searchRecord() {
        List<FundInvestmentRecord> fundInvestmentRecordList = fundService.search();
        JSONArray array= JSONArray.parseArray(JSON.toJSONString(fundInvestmentRecordList));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg", "");
        jsonObject.put("code", 0);
        jsonObject.put("data", array);
        jsonObject.put("count", fundInvestmentRecordList.size());
        return jsonObject.toString();
    }
}
