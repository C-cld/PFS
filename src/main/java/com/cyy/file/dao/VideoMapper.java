package com.cyy.file.dao;

import com.cyy.file.domain.Video;
import com.cyy.file.domain.VideoToTag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface VideoMapper {
    int total(@Param("tagIds") String[] tagIds, @Param("arrLength") int arrLength);
    List<Video> searchVideo(@Param("tagIds") String[] tagIds, @Param("arrLength") int arrLength, int index, int limit);
    void insertVideo(Video video);
    void insertVideoToTag(VideoToTag fileToTag);
    void deleteVideo(@Param("videoId") String videoId);
    void deleteVideoToTag(@Param("videoId") String videoId);
}
