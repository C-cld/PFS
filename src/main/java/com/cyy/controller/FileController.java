package com.cyy.controller;

import com.cyy.domain.Tag;
import com.cyy.domain.UploadFile;
import com.cyy.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;
import java.util.UUID;

@Controller
public class FileController {
    @Autowired
    private FileService fileService;

    @RequestMapping(value = "/porn")
    public ModelAndView porn() {
        ModelAndView mav = new ModelAndView("porn");
        List<Tag> tagList = fileService.findTag(null);
        mav.addObject("tagList", tagList);
        return mav;
    }

    @RequestMapping(value = "/upload-file", method = RequestMethod.POST)
    @ResponseBody
    public String upload(@RequestParam("videoFile") MultipartFile uploadedFile, @RequestParam("videoTags") String tagIds) {
        String fileName = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        String originalName = uploadedFile.getOriginalFilename();
        long size = uploadedFile.getSize();
        UploadFile file = new UploadFile();
        file.setId(fileName);
        file.setOriginalName(originalName);
        file.setName(fileName);
        file.setSize(size);

        String[] tagIdArr = tagIds.split(",");

        // 上传到移动硬盘，并添加到数据库
        try {
            fileService.uploadFile(file, uploadedFile, tagIdArr);
        } catch (Exception e) {
            e.printStackTrace();
            return "上传失败";
        }
        return "success";
    }

    @RequestMapping(value = "/play-video")
    @ResponseBody
    public void play(HttpServletRequest request , HttpServletResponse response) {
        BufferedOutputStream outputStream;
        RandomAccessFile randomAccessFile = null;
        try {
            File file = new File(fileService.uploadPath + "3dc59afe6bd9480c95eb626f1bf962aa");
            //开始下载位置
            long startByte = 0;
            //结束下载位置
            long endByte = file.length() - 1;
            response.setHeader("Accept-Ranges", "bytes");
            String range = request.getHeader("Range");
            if (range != null && range.contains("bytes=") && range.contains("-")) {
                range = range.substring(range.lastIndexOf("=") + 1).trim();
                String[] ranges = range.split("-");
                //判断range的类型
                if (ranges.length == 1) {
                    //类型一：bytes=-2343
                    if (range.startsWith("-")) {
                        endByte = Long.parseLong(ranges[0]);
                    }
                    //类型二：bytes=2343-
                    else if (range.endsWith("-")) {
                        startByte = Long.parseLong(ranges[0]);
                    }
                }
                //类型三：bytes=22-2343
                else if (ranges.length == 2) {
                    startByte = Long.parseLong(ranges[0]);
                    endByte = Long.parseLong(ranges[1]);
                }
            }
            //要下载的长度
            long contentLength = endByte - startByte + 1;
            response.setHeader("Accept-Ranges", "bytes");
            response.setStatus(response.SC_PARTIAL_CONTENT);
            response.setHeader("Content-Range", "bytes " + startByte + "-" + endByte + "/" + file.length());

            outputStream = new BufferedOutputStream(response.getOutputStream());
            randomAccessFile = new RandomAccessFile(file, "r");
            //已传送数据大小
            long transmitted = 0;
            byte[] buff = new byte[4096];
            int len = 0;
            randomAccessFile.seek(startByte);
            //判断是否到了最后不足4096（buff的length）个byte这个逻辑（(transmitted + len) <= contentLength）要放前面
            //不然会会先读取randomAccessFile，造成后面读取位置出错，找了一天才发现问题所在
            while ((transmitted + len) <= contentLength && (len = randomAccessFile.read(buff)) != -1) {
                outputStream.write(buff, 0, len);
                transmitted += len;
            }
            //处理不足buff.length部分
            if (transmitted < contentLength) {
                len = randomAccessFile.read(buff, 0, (int) (contentLength - transmitted));
                outputStream.write(buff, 0, len);
            }
            outputStream.flush();
            response.flushBuffer();
            randomAccessFile.close();

        } catch (Exception ignored) {

        } finally {
            try {
                if (randomAccessFile != null) {
                    randomAccessFile.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @RequestMapping(value = "/add-tag")
    @ResponseBody
    public Tag addTag(String tagName) {
        Tag tag = new Tag();
        tag.setId(UUID.randomUUID().toString().replace("-", "").toLowerCase());
        tag.setName(tagName);
        try {
            fileService.addTag(tag);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return tag;
    }
}
