package com.cyy.finance.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cyy.finance.domain.Profit;
import com.cyy.finance.model.ProfitSumVO;
import com.cyy.finance.model.ProfitSumPerDayVO;
import com.cyy.finance.service.ProfitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
public class ProfitController {
    @Autowired
    ProfitService profitService;

    private static final Logger log = LoggerFactory.getLogger(ProfitController.class);

    /**
     * 收益页
     * @return ModelAndView
     */
    @RequestMapping(value = "/profit")
    public ModelAndView profitPage() {
        ModelAndView modelAndView = new ModelAndView("finance/profit");
        return modelAndView;
    }

    /**
     * 每日历史总收益
     * @return List
     */
    @RequestMapping(value = "/profit-sum-per-day")
    @ResponseBody
    List<ProfitSumPerDayVO> getProfitSumPerDayList() {
        return profitService.getProfitSumPerDayVOList();

    }

    /**
     * 我的基金表格
     * @return String
     */
    @RequestMapping(value = "/my-product")
    @ResponseBody
    public String myProduct() {
        List<ProfitSumVO> profitSumVOList = profitService.getProfitSumVOList();
        JSONArray jsonArray = (JSONArray) JSONArray.toJSON(profitSumVOList);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg", "");
        jsonObject.put("code", 0);
        jsonObject.put("data", jsonArray);
        jsonObject.put("count", profitSumVOList.size());
        return jsonObject.toString();
    }

    /**
     * 基金收益详情表格
     * @param page 当前页
     * @param limit 每页大小
     * @param productId 代码
     * @return String
     */
    @RequestMapping(value = "/profit-detail-list")
    @ResponseBody
    public String getProfitDetailList(Integer page, Integer limit, String productId) {
        List<Profit> profitDetailList = profitService.getProfitDetailList(page, limit, productId);
        int count = profitService.getCount(productId);
        JSONArray jsonArray = (JSONArray) JSONArray.toJSON(profitDetailList);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg", "");
        jsonObject.put("code", 0);
        jsonObject.put("data", jsonArray);
        jsonObject.put("count", count);
        return jsonObject.toString();
    }

    /**
     * 录入每日收益
     * @param productId 代码
     * @param createDate 日期
     * @param profit 收益
     * @return
     */
    @RequestMapping(value = "/add-profit-detail")
    @ResponseBody
    public boolean addProfitDetail(String productId, String createDate, Float profit) {
        try {
            return profitService.addProfitDetail(productId, createDate, profit);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping(value = "/get-total-profit")
    @ResponseBody
    public Float getTotalProfit() {
        return profitService.getTotalProfit();
    }

    @RequestMapping(value = "/percentage")
    @ResponseBody
    public Map<Integer, Float> getPercentage() {
        return profitService.getPercentage();
    }
}
