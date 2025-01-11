package com.wrestling_moves.service;

import com.wrestling_moves.entity.Video;
import com.wrestling_moves.repository.VideoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class VideoService {
    
    private final VideoRepository videoRepository;

    public VideoService(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    public Optional<Video> findVideoById(Long id){
        return videoRepository.findById(id);
    }

    public List<Video> findAll() {
        return videoRepository.findAll();
    }

    public Video findByUrl(String url){
        return videoRepository.findByUrl(url);
    }

    public List<Video> findByCategory(Video.Category category) {
        return videoRepository.findByCategory(category);
    }

    public List<Video> findByTitle(String title) {
        return videoRepository.findByTitle(title);
    }

    public List<Video> findByDescription(String description) {
        return videoRepository.findByDescription(description);
    }

    public Video save(Video video) {
        return videoRepository.save(video);
    }

    public void delete(Video video) {
        videoRepository.delete(video);
    }

    public void delete(Long id) {
        videoRepository.deleteById(id);
    }

    public void deleteAll() {
        videoRepository.deleteAll();
    }

}
