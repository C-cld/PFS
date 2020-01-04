package com.cyy.finance.controller;

import com.cyy.finance.domain.Product;
import com.cyy.finance.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    ProductService productService;

    @RequestMapping(value = "/get-product-list")
    @ResponseBody
    public List<Product> getProductList() {
        return productService.getProductList();
    }

}
