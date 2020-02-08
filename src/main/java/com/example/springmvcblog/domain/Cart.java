package com.example.springmvcblog.domain;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class Cart {

    private int cartSize;

    private List<Integer> buyItemsQuantity=
            new ArrayList<>();

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

    public List<Integer> getBuyItemsQuantity() {
        return buyItemsQuantity;
    }

    public void setBuyItemsQuantity(List<Integer> buyItemsQuantity) {
        this.buyItemsQuantity = buyItemsQuantity;
    }

    public int cartSize(){

        if(cartItems.isEmpty()){
            return 0;
        }
        this.cartSize=this.cartItems.size();
        return this.cartSize;
    }

    public int getCartSize() {
        return cartSize;
    }

    public void setCartSize(int cartSize) {
        this.cartSize = cartSize;
    }
}
