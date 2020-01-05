package com.cyy.finance.dao;

import com.cyy.finance.domain.Product;
import com.cyy.finance.domain.Record;
import com.cyy.finance.model.ConfirmSumVO;
import com.cyy.finance.model.RecordVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductMapper {
    List<Product> getProductList();
    List<RecordVO> productChangeRecord();
    Integer getProductChangeRecordCount();
    boolean addProductChangeRecord(Record record);
    List<ConfirmSumVO> productConfirmSum();
}
