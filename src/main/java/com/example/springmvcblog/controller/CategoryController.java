package com.example.springmvcblog.controller;

import com.example.springmvcblog.domain.Category;
import com.example.springmvcblog.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/category")
    public String create(Model model){
        model.addAttribute("category",new Category());
        return "admin/categoryForm";
    }

    @PostMapping("/category")
    public String process(@Valid Category category, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/categoryForm";
        }
        categoryService.create(category);
        return "redirect:/categories";
    }
    @GetMapping("/categories")
    public String showAllCategories(Model model){
        model.addAttribute("categories",categoryService.findAll());
        return "admin/categories";
    }
}
