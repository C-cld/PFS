package com.cyy.file.dao;

import com.cyy.file.domain.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TagMapper {
    List<Tag> searchTag(@Param("tagIds") String[] tagIds);
    void insertTag(Tag tag);
    void updateTagNameById(@Param("tagId") String tagId, @Param("newTagName") String newTagName);
    void deleteTag(@Param("tagId") String tagId);
}
