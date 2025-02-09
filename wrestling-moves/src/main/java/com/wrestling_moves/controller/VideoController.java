package com.wrestling_moves.controller;

import com.wrestling_moves.entity.Video;
import com.wrestling_moves.service.VideoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;

@Controller
public class VideoController {

    private final VideoService videoService;

    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @GetMapping("dashboard/videos")
    public String displayVideos() {
        return "videosForBeginners";
    }

}

