package com.example.pathfinder.service.Impl;

import com.example.pathfinder.model.entity.Category;
import com.example.pathfinder.model.enums.CategoryNames;
import com.example.pathfinder.repository.CategoryRepository;
import com.example.pathfinder.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category findByName(CategoryNames c) {
        return categoryRepository
                .findByName(c)
                .orElse(null);
    }

}
