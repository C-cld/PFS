package com.cyy.service;

import com.cyy.dao.FileMapper;
import com.cyy.domain.UploadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class FileService {
    @Autowired
    FileMapper fileMapper;

    @Value("${uploadPath}")
    public String uploadPath;

    public void uploadFile(UploadFile uploadFile, MultipartFile uploadedFile) throws Exception {
        File dest = new File(uploadPath + uploadFile.getName());
        uploadedFile.transferTo(dest);
        fileMapper.add(uploadFile);
    }
}
