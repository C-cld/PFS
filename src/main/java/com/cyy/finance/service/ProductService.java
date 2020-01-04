package com.cyy.finance.service;

import com.cyy.finance.dao.ProductMapper;
import com.cyy.finance.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductMapper productMapper;

    public List<Product> getProductList() {
        return productMapper.getProductList();
    }
}
