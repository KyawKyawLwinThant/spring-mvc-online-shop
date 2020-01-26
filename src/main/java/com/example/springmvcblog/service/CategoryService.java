package com.example.springmvcblog.service;

import com.example.springmvcblog.domain.Category;

import java.util.List;

public interface CategoryService {
    Category create(Category category);
    Category findById(int id);
    List<Category> findAll();
}
