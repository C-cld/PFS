package com.cyy.service;

import com.cyy.dao.FileMapper;
import com.cyy.domain.FileToTag;
import com.cyy.domain.Tag;
import com.cyy.domain.UploadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class FileService {
    @Autowired
    FileMapper fileMapper;

    @Value("${uploadPath}")
    public String uploadPath;

    public boolean uploadFile(UploadFile uploadFile, MultipartFile uploadedFile, String[] tagIdArr) throws Exception {
        File dest = new File(uploadPath + uploadFile.getName());
        // 1.保存到硬盘
        uploadedFile.transferTo(dest);
        // 2. 保存到数据库
        fileMapper.addFile(uploadFile);
        // 3. 关联标签
        for (String tagId : tagIdArr) {
            FileToTag ftt = new FileToTag();
            ftt.setId(UUID.randomUUID().toString().replace("-", "").toLowerCase());
            ftt.setFileId(uploadFile.getId());
            ftt.setTagId(tagId);
            fileMapper.fileToTag(ftt);
        }
        return true;
    }

    public List<Tag> findTag(String[] tagIds) {
        List<Tag> tagList = fileMapper.findTag(tagIds);
        return tagList;
    }

    public void addTag(Tag tag) {
        fileMapper.addTag(tag);
    }

    public List<UploadFile> findFile(String[] tagIds) {
        List<UploadFile> uploadFileList = fileMapper.findFile(tagIds, tagIds == null ? 0 : tagIds.length);
        return uploadFileList;
    }
}
