package com.cyy.file.service;

import com.cyy.file.dao.VideoMapper;
import com.cyy.file.domain.Video;
import com.cyy.file.domain.VideoToTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
public class VideoService {
    @Autowired
    VideoMapper videoMapper;

    @Value("${uploadPath}")
    public String uploadPath;

    public int getTotal(String[] tagIds) {
        int total = videoMapper.total(tagIds, tagIds == null ? 0 : tagIds.length);
        return total;
    }

    public List<Video> searchVideo(String[] tagIds, int index, int limit) {
        List<Video> uploadFileList = videoMapper.searchVideo(tagIds, tagIds == null ? 0 : tagIds.length, index, limit);
        return uploadFileList;
    }

    public boolean uploadVideo(Video video, MultipartFile uploadedVideo, String[] tagIdArr) throws Exception {
        File dest = new File(uploadPath + video.getName());
        // 1.保存到硬盘
        uploadedVideo.transferTo(dest);
        // 2. 保存到数据库
        videoMapper.insertVideo(video);
        // 3. 关联标签
        if (tagIdArr != null) {
            for (String tagId : tagIdArr) {
                VideoToTag ftt = new VideoToTag();
                ftt.setId(UUID.randomUUID().toString().replace("-", "").toLowerCase());
                ftt.setFileId(video.getId());
                ftt.setTagId(tagId);
                videoMapper.insertVideoToTag(ftt);
            }
        }
        return true;
    }

    public void deleteVideo(String videoId) {
        videoMapper.deleteVideo(videoId);
        // 删除标签关联
        videoMapper.deleteVideoToTag(videoId);
    }
}
