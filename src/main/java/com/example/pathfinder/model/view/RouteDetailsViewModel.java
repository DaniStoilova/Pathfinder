package com.example.pathfinder.model.view;

import com.example.pathfinder.model.entity.Picture;
import com.example.pathfinder.model.enums.Level;

import java.util.Set;

public class RouteDetailsViewModel {

    private Long id;
    private String name;
    private Level level;
    private String description;
    private String videoUrl;
    private String authorName;

    private Set<Picture> pictures;



    public RouteDetailsViewModel() {
    }

    public Set<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(Set<Picture> pictures) {
        this.pictures = pictures;
    }

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

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }


    // TODO pass coordinates;
}