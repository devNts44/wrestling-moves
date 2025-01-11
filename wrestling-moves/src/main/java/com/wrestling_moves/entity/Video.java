package com.wrestling_moves.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "VIDEO")
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le titre est obligatoire")
    @Column(nullable = false)
    private String title;

    @NotBlank(message = "La description est obligatoire")
    @Column(nullable = false)
    private String description;

    @NotBlank(message = "L'url est obligatoire")
    @Column(nullable = false, unique = true)
    private String url;

    @NotBlank(message = "La catégorie est obligatoire")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;

    public enum Category {
        BEGINNER,
        INTERMEDIATE,
        ADVANCED
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
