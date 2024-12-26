package com.wrestling_moves.service;

import com.wrestling_moves.entity.Video;
import com.wrestling_moves.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VideoService {
    @Autowired
    private VideoRepository videoRepository;

    public List<Video> getAllVideos() {
        return videoRepository.findAll();
    }

    public void saveVideo(Video video) {
        videoRepository.save(video);
    }

    public void deleteVideo(Video video){
        videoRepository.delete(video);
    }

    public Optional<Video> findVideo (Long id){
        return videoRepository.findById(id);
    }
}
