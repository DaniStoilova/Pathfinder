package com.example.pathfinder.model.binding;

import com.example.pathfinder.model.entity.Category;
import com.example.pathfinder.model.entity.User;
import com.example.pathfinder.model.enums.CategoryNames;
import com.example.pathfinder.model.enums.Level;
import jakarta.persistence.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

public class AddRouteBindingModel {

    private String name;


    private String description;

    private MultipartFile gpxCoordinates;


    private Level level;

    private String videoUrl;

    private Set<CategoryNames> categories;
    private User author;



    public AddRouteBindingModel() {
    }

    public MultipartFile getGpxCoordinates() {
        return gpxCoordinates;
    }

    public void setGpxCoordinates(MultipartFile gpxCoordinates) {
        this.gpxCoordinates = gpxCoordinates;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public Set<CategoryNames> getCategories() {
        return categories;
    }

    public void setCategories(Set<CategoryNames> categories) {
        this.categories = categories;
    }
}
