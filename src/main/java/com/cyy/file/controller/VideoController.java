package com.cyy.file.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cyy.file.domain.Tag;
import com.cyy.file.domain.Video;
import com.cyy.file.service.TagService;
import com.cyy.file.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
public class VideoController {
    @Autowired
    private VideoService videoService;
    @Autowired
    private TagService tagService;

    /**
     * 视频页面
     * @return
     */
    @RequestMapping(value = "/video")
    public ModelAndView videoPage() {
        ModelAndView mav = new ModelAndView("/file/video");
        List<Tag> tagList = tagService.searchTag(null);
        mav.addObject("tagList", tagList);
        return mav;
    }

    /**
     * 播放页面
     * @param videoId
     * @return
     */
    @RequestMapping(value = "/video-player")
    public ModelAndView videoPlayerPage(String videoId) {
        ModelAndView mav = new ModelAndView("/file/video-player");
        mav.addObject("fileId", videoId);
        return mav;
    }

    /**
     * 条件查询视频
     * @param tagId
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping(value = "/search-video")
    @ResponseBody
    public String searchVideo(@RequestParam(value = "tagIds", required = false)String tagId, @RequestParam(value = "page") int page, @RequestParam(value = "limit") int limit) {
        String[] tagIds = null;
        if (tagId != null && !tagId.equals("")) {
            tagIds = tagId.split(",");
        }
        int total = videoService.getTotal(tagIds); // 总数
        int index = (page - 1) * limit; // 起始位置

        List<Video> videoList = videoService.searchVideo(tagIds, index, limit);
        JSONArray array= JSONArray.parseArray(JSON.toJSONString(videoList));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg", "");
        jsonObject.put("code", 0);
        jsonObject.put("data", array);
        jsonObject.put("count", total);
        return jsonObject.toString();
    }

    /**
     * 上传视频
     * @param uploadedVideo
     * @param tagIds
     * @return
     */
    @RequestMapping(value = "/upload-video")
    @ResponseBody
    public boolean upload(@RequestParam("file") MultipartFile uploadedVideo, @RequestParam("videoTags") String tagIds) {
        String videoName = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        String originalName = uploadedVideo.getOriginalFilename();
        double size = (double)uploadedVideo.getSize() / 1048576;
        Video video = new Video();
        video.setId(videoName);
        video.setOriginalName(originalName);
        video.setName(videoName);
        video.setSize(size);

        String[] tagIdArr = tagIds.split(",");
        if ("".equals(tagIds.trim())) {
            tagIdArr = null;
        }

        // 上传到移动硬盘，并添加到数据库
        try {
            return videoService.uploadVideo(video, uploadedVideo, tagIdArr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 删除视频
     * @param videoId
     * @return
     */
    @RequestMapping(value = "/delete-video")
    @ResponseBody
    public boolean deleteVideo(String videoId) {
        try {
            videoService.deleteVideo(videoId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 播放视频
     * @param request
     * @param response
     */
    @RequestMapping(value = "/play-video")
    @ResponseBody
    public void play(HttpServletRequest request , HttpServletResponse response) {
        String id = request.getParameter("fileId");
        BufferedOutputStream outputStream;
        RandomAccessFile randomAccessFile = null;
        try {
            File file = new File(videoService.uploadPath + id);
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
}
