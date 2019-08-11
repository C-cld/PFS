package com.cyy.controller;

import org.apache.catalina.connector.ClientAbortException;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.UUID;

@Controller
public class FileController {
    @RequestMapping(value = "/porn")
    public ModelAndView porn() {
        ModelAndView mav = new ModelAndView("porn");
        return mav;
    }

    @RequestMapping(value = "/upload-file", method = RequestMethod.POST)
    @ResponseBody
    public String upload(@RequestParam("videoFile") MultipartFile uploadfile, @RequestParam("videoTags") String tagIds) {
        //String fileName = uploadfile.getOriginalFilename();
        String fileName = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        String filePath = "E://";
        File dest = new File(filePath + fileName);
        try {
            uploadfile.transferTo(dest);
            return "上传成功";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "上传失败！";
    }

    @RequestMapping(value = "/play-video")
    @ResponseBody
    public void play(HttpServletRequest request , HttpServletResponse response) {
        BufferedOutputStream outputStream = null;
        RandomAccessFile randomAccessFile = null;
        try {
            File file = new File("E://uploadvideo/7115a6d9e67e4ef5b0468f23bedfe46f");
            //开始下载位置
            long startByte = 0;
            //结束下载位置
            long endByte = file.length() - 1;
            response.setHeader("Accept-Ranges", "bytes");
            String range = request.getHeader("Range");
            if (range != null && range.contains("bytes=") && range.contains("-")) {
                range = range.substring(range.lastIndexOf("=") + 1).trim();
                String ranges[] = range.split("-");
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
            //文件名
            String fileName = file.getName();
            //文件类型
            String contentType = request.getServletContext().getMimeType(fileName);
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
                transmitted += len;
            }
            outputStream.flush();
            response.flushBuffer();
            randomAccessFile.close();

        } catch (Exception e) {

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
}
