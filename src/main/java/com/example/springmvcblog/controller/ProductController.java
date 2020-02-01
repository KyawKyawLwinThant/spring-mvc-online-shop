package com.example.springmvcblog.controller;

import com.example.springmvcblog.domain.Products;
import com.example.springmvcblog.service.CategoryService;
import com.example.springmvcblog.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ProductController {

    private Logger logger=LoggerFactory.getLogger(this.getClass());

    private final ProductService productService;
    private final CategoryService categoryService;

    public ProductController(ProductService productService,
                             CategoryService categoryService) {
        this.productService = productService;
        this.categoryService=categoryService;
    }

    @GetMapping("/product")
    public String create(Model model){
        model.addAttribute("products",new Products());
        model.addAttribute("categories",categoryService.findAll());
        return "admin/productsForm";
    }

    @PostMapping("/product")
    public String process(Products products, BindingResult result, Model model
    , RedirectAttributes redirectAttributes){
        logger.info("You got it. Products From Process ");

        if(result.hasErrors()){
            model.addAttribute("categories",categoryService.findAll());
            return "admin/productsForm";
        }
        productService.create(products);
        redirectAttributes.addFlashAttribute("success",true);
        return  "redirect:/products";
    }

    @GetMapping("/products")
    public String showAllProducts(Model model){
        model.addAttribute("products",productService.findAll());
        model.addAttribute("success"
                ,model.containsAttribute("success"));
        return "admin/products";

    }
}
