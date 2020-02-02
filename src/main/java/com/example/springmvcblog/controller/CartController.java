package com.example.springmvcblog.controller;

import com.example.springmvcblog.domain.Cart;
import com.example.springmvcblog.domain.Products;
import com.example.springmvcblog.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class CartController {

    private final Cart cart;
    private final ProductService productService;

    public CartController(Cart cart,ProductService productService) {
        this.cart = cart;
        this.productService=productService;
    }
    @GetMapping("/cart/add/{id}")
    public String addToCart(@PathVariable int id, HttpServletRequest request){
        boolean isExisted=false;
        for(Products products:cart.getCartItems()){
            if(products.getId()==id){
                isExisted=true;
            }
        }
        if(!isExisted){
            cart.addToCart(productService.findById(id));
        }

        HttpSession session=request.getSession();
        session.setAttribute("cartSize",cart.cartSize());
        return "redirect:/products/"+id;
    }
}
