package com.example.springmvcblog.service;

import com.example.springmvcblog.domain.Products;

import java.util.List;

public interface ProductService {

    Products create(Products products);
    List<Products> findAll();
    Products findById(int id);
}
