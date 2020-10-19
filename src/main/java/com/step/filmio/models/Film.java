package com.step.filmio.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Film {

    public Film(String name, String details, String imageUrl, String videoUrl) {
        this.name = name;
        this.details = details;
        this.imageUrl = imageUrl;
        this.videoUrl = videoUrl;
        this.score = 0;
    }

    public Film() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String details;

    private int score;

    private String imageUrl;
    private String videoUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String image_url) {
        this.imageUrl = image_url;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String video_url) {
        this.videoUrl = video_url;
    }

    public Film(String name, String details, int score, String imageUrl, String video_url) {
        this.name = name;
        this.details = details;
        this.score = score;
        this.imageUrl = imageUrl;
        this.videoUrl = video_url;
    }

    public Film(Long id, String name, String details, int score, String imageUrl, String video_url) {
        this.id = id;
        this.name = name;
        this.details = details;
        this.score = score;
        this.imageUrl = imageUrl;
        this.videoUrl = video_url;
    }
}
