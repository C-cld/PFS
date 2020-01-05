package com.cyy.finance.service;

import com.cyy.finance.dao.ProductMapper;
import com.cyy.finance.domain.Product;
import com.cyy.finance.domain.Record;
import com.cyy.finance.model.ConfirmSumVO;
import com.cyy.finance.model.RecordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    ProductMapper productMapper;

    public List<Product> getProductList() {
        return productMapper.getProductList();
    }

    public List<RecordVO> productChangeRecord() {
        return productMapper.productChangeRecord();
    }

    public int getProductChangeRecordCount() {
        return productMapper.getProductChangeRecordCount();
    }

    public boolean addProductChangeRecord(Record record) {
        record.setId(UUID.randomUUID().toString().replace("-", "").toLowerCase());
        return productMapper.addProductChangeRecord(record);
    }

    public List<ConfirmSumVO> productConfirmSum() {
        return productMapper.productConfirmSum();
    }


}
