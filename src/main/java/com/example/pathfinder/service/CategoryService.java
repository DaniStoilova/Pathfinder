package com.example.pathfinder.service;

import com.example.pathfinder.model.entity.Category;
import com.example.pathfinder.model.enums.CategoryNames;
import org.springframework.stereotype.Service;


public interface CategoryService {
    Category findByName(CategoryNames c);
}
