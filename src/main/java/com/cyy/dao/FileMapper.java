package com.cyy.dao;

import com.cyy.domain.FileToTag;
import com.cyy.domain.Tag;
import com.cyy.domain.UploadFile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FileMapper {
    void addFile(UploadFile uploadFile);
    void fileToTag(FileToTag fileToTag);
    void addTag(Tag tag);
    List<Tag> findTag(@Param("tagIds") String[] tagIds);
}
