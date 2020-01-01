package com.cyy.finance.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cyy.finance.domain.Fund;
import com.cyy.finance.domain.Record;
import com.cyy.finance.domain.FundNet;
import com.cyy.finance.service.FundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class FundController {

    @Autowired
    FundService fundService;

    /**
     * 基金定投记录页
     * @return
     */
    @RequestMapping(value = "/fund")
    public ModelAndView fundPage() {
        ModelAndView mav = new ModelAndView("finance/fund");
        try {
            // 我的基金
            List<Fund> fundList = fundService.searchFund();
            mav.addObject("fundList", fundList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mav;
    }

    /**
     * 记录添加页
     * @return
     */
    @RequestMapping(value = "/fund-investment-record-add")
    public ModelAndView fundInvestmentRecordAddPage() {
        ModelAndView mav = new ModelAndView("finance/fund-investment-record-add");
        return mav;
    }

    /**
     * 基金定投记录页表格数据
     * @return
     */
    @RequestMapping(value = "/fund-investment-record")
    @ResponseBody
    public String fundInvestmentRecord() {
        try {
            List<Record> recordList = fundService.searchInvestmentRecord(null);
            JSONArray array= JSONArray.parseArray(JSON.toJSONString(recordList));
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("msg", "");
            jsonObject.put("code", 0);
            jsonObject.put("data", array);
            jsonObject.put("count", recordList.size());
            return jsonObject.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 获取图表标记点
     * @param fundCode
     * @return
     */
    @RequestMapping(value = "/getMarkPoint")
    @ResponseBody
    public List<Record> getMarkPoint(String fundCode) {
        try {
            List<Record> recordList = fundService.searchInvestmentRecord(fundCode);
            return recordList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 获取基金净值
     * @param fundCode
     * @return
     */
    @RequestMapping(value = "/getFundNet")
    @ResponseBody
    public List<FundNet> getFundNet(String fundCode) {
        List<FundNet> fundNetList = new ArrayList<>();
        try {
            fundNetList = fundService.getFundNet(fundCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fundNetList;
    }

    /**
     * 获取基金标记点
     * @param fundCode
     * @return
     */
    @RequestMapping(value = "/getFundMarkPoint")
    @ResponseBody
    public List<Record> getFundMarkPoint(String fundCode) {
        List<Record> recordList = new ArrayList<>();
        try {
            recordList = fundService.searchInvestmentRecord(fundCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return recordList;
    }

    @RequestMapping(value = "/insert-fund")
    @ResponseBody
    public boolean insertFund(String fundCode) {
        try {
            fundService.insertFund(fundCode);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

}
