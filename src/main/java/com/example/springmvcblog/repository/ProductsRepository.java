package com.example.springmvcblog.repository;

import com.example.springmvcblog.domain.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepository extends JpaRepository<Products,Integer> {
}
