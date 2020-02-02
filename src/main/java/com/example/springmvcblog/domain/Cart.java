package com.example.springmvcblog.domain;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class Cart {

    private Set<Products> cartItems=new HashSet<>();
    public void addToCart(Products products){
        this.cartItems.add(products);
    }

    public void clearCart(){
        this.cartItems.clear();
    }

    public Set<Products> getCartItems(){
        return this.cartItems;
    }

    public int cartSize(){
        if(cartItems.isEmpty()){
            return 0;
        }
        return this.cartItems.size();
    }

}
