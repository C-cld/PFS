package com.cyy.file.service;

import com.cyy.file.dao.TagMapper;
import com.cyy.file.domain.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {
    @Autowired
    TagMapper tagMapper;

    public List<Tag> searchTag(String[] tagIds) {
        List<Tag> tagList = tagMapper.searchTag(tagIds);
        return tagList;
    }

    public void insertTag(Tag tag) {
        tagMapper.insertTag(tag);
    }

    public void updateTagName(String tagId, String newTagName) {
        tagMapper.updateTagNameById(tagId, newTagName);
    }

    public void deleteTag(String tagId) {
        tagMapper.deleteTag(tagId);
    }
}
