package com.wrestling_moves.repository;

import com.wrestling_moves.entity.Video;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface VideoRepository extends JpaRepository<Video, Long> {

    Video findByUrl(String url);

    List<Video> findByCategory(Video.Category category);

    List<Video> findByTitle(String title);

    List<Video> findByDescription(String description);



}