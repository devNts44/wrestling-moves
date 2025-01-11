package com.wrestling_moves.controller;

import com.wrestling_moves.entity.Video;
import com.wrestling_moves.service.VideoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;

@RestController
public class VideoController {

    private final VideoService videoService;

    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @GetMapping("/videos")
    List<Video> findAll() {
        return videoService.findAll();
    }

    @GetMapping("/videos/{id}")
    Optional<Video> getVideoById(@PathVariable Long id) {
        return videoService.findVideoById(id);
    }

    @GetMapping("/videos/category/{category}")
    List<Video> findByCategory(@PathVariable Video.Category category) {
        return videoService.findByCategory(category);
    }

    @GetMapping("/videos/title/{title}")
    List<Video> findByTitle(@PathVariable String title) {
        return videoService.findByTitle(title);
    }

    @GetMapping("/videos/description/{description}")
    List<Video> findByDescription(@PathVariable String description) {
        return videoService.findByDescription(description);
    }
    
}

