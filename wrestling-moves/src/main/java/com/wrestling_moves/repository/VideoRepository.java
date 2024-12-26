package com.wrestling_moves.repository;

import com.wrestling_moves.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video, Long> {

    Video findByUrl(String url);
}