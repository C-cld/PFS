package com.cyy.file.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cyy.file.domain.Tag;
import com.cyy.file.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.UUID;

@Controller
public class TagController {
    @Autowired
    TagService tagService;

    /**
     * 搜索标签
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping(value = "/search-tag")
    @ResponseBody
    public String searchTag(@RequestParam(value = "page") int page, @RequestParam(value = "limit") int limit) {
        List<Tag> tagList = tagService.searchTag(null);
        int startIndex = (page - 1) * limit;
        int endIndex = startIndex + limit;
        int total = tagList.size();
        // 分页截取，标签不多，所以直接用假分页
        List<Tag> subList = tagList.subList(startIndex, endIndex > total ? total : endIndex);
        JSONArray array= JSONArray.parseArray(JSON.toJSONString(subList));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg", "");
        jsonObject.put("code", 0);
        jsonObject.put("data", array);
        jsonObject.put("count", total);
        return jsonObject.toString();
    }

    /**
     * 新增标签
     * @param tagName
     * @return
     */
    @RequestMapping(value = "/insert-tag")
    @ResponseBody
    public Tag addTag(String tagName) {
        Tag tag = new Tag();
        tag.setId(UUID.randomUUID().toString().replace("-", "").toLowerCase());
        tag.setName(tagName);
        try {
            tagService.insertTag(tag);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return tag;
    }

    /**
     * 更新标签名称
     * @param tagId
     * @param newName
     * @return
     */
    @RequestMapping(value = "/update-tag-name")
    @ResponseBody
    public boolean updateTagName(String tagId, String newName) {
        try {
            tagService.updateTagName(tagId, newName);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除标签
     * @param tagId
     * @return
     */
    @RequestMapping(value = "/delete-tag")
    @ResponseBody
    public boolean deleteTag(String tagId) {
        try {
            tagService.deleteTag(tagId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
