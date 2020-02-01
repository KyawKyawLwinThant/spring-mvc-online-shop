package com.example.springmvcblog.service;

import com.example.springmvcblog.domain.Products;
import com.example.springmvcblog.repository.ProductsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductsRepository productsRepository;

    public ProductServiceImpl(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @Override
    public Products create(Products products) {
        return productsRepository.save(products);
    }

    @Override
    public List<Products> findAll() {
        return productsRepository.findAll();
    }

    @Override
    public Products findById(int id) {
        return productsRepository.findById(id).orElse(null);
    }
}
