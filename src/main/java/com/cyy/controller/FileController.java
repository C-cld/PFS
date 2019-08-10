package com.cyy.controller;

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

        File file = new File("E://uploadvideo/7115a6d9e67e4ef5b0468f23bedfe46f");
        FileInputStream input = null;
        OutputStream output = null;
        try {
            input = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int i = 0;
        try {
            i = input.available();
            byte[] bytes = new byte[i];
            input.read(bytes);
            response.setContentType("application/video");
            output = response.getOutputStream();
            output.write(bytes);
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                output.close();
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
