package com.example.pathfinder.model.entity;

import com.example.pathfinder.model.enums.CategoryNames;
import jakarta.persistence.*;

@Entity
@Table(name="categories")
public class Category extends BaseEntity {


   @Enumerated(EnumType.STRING)
    private CategoryNames name;

    @Column(columnDefinition = "TEXT")
    private String description;

    public CategoryNames getName() {
        return name;
    }

    public void setName(CategoryNames name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category() {
    }
}
