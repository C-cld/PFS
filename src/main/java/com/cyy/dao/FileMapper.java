package com.cyy.dao;

import com.cyy.domain.UploadFile;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileMapper {
    void add(UploadFile uploadFile);
}
