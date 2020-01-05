package com.cyy.finance.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cyy.finance.domain.Product;
import com.cyy.finance.domain.Record;
import com.cyy.finance.model.ConfirmSumVO;
import com.cyy.finance.model.RecordVO;
import com.cyy.finance.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    ProductService productService;

    @RequestMapping(value = "/product")
    public ModelAndView productPage() {
        return new ModelAndView("finance/product");
    }

    @RequestMapping(value = "/get-product-list")
    @ResponseBody
    public List<Product> getProductList() {
        return productService.getProductList();
    }

    @RequestMapping(value = "/product-change-record")
    @ResponseBody
    public String productChangeRecord() {
        List<RecordVO> recordList = productService.productChangeRecord();
        int count = productService.getProductChangeRecordCount();
        JSONArray jsonArray = (JSONArray) JSONArray.toJSON(recordList);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg", "");
        jsonObject.put("code", 0);
        jsonObject.put("data", jsonArray);
        jsonObject.put("count", count);
        return jsonObject.toString();
    }

    @PostMapping(value = "/add-product-change-record")
    @ResponseBody
    public boolean addProductChangeRecord(@RequestBody Record record) {
        try {
            return productService.addProductChangeRecord(record);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping(value = "/product-confirm-sum")
    @ResponseBody
    public List<ConfirmSumVO> productConfirmSum() {
        return productService.productConfirmSum();
    }

}
