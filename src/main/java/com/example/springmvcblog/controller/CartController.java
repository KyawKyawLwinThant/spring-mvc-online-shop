package com.example.springmvcblog.controller;

import com.example.springmvcblog.domain.Cart;
import com.example.springmvcblog.domain.Products;
import com.example.springmvcblog.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Set;

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

    @GetMapping("/cart/cartview")
    public String cartView(Model model){
        model.addAttribute("cartsItems",cart.getCartItems());
        return "user/cartView";
    }
    @GetMapping("/cart/clear")
    public String clearCart(HttpServletRequest request){
        HttpSession session=request.getSession(false);
        this.cart.clearCart();
        this.cart.setCartSize(0);
        session.setAttribute("cartSize",cart.cartSize());
        return "redirect:/cart/cartview";
    }
    @GetMapping("/cart/checkoutView")
    public String checkoutView(Model model){
        model.addAttribute("products",cart.getCartItems());
        model.addAttribute("cart",new Cart());
        return "user/checkout";
    }
    @PostMapping("/cart/calculate")
    public String calculate(Cart cartQuantity,Model model){
        Set<Products> products=this.cart.getCartItems();
        double sum=0;
        int i=0;
        for(Products product:products){
            product.setCartItemQuantity(cartQuantity.getBuyItemsQuantity().get(i));
            sum+=product.getPrice() * product.getCartItemQuantity();
            i++;
        }
        this.sum=sum;
       // model.addAttribute("total",sum);
       // model.addAttribute("carts",cart.getCartItems());
        return "redirect:/balance";
    }
    @GetMapping("/balance")
    public String viewBalance(Model model){
        model.addAttribute("total",sum);
        model.addAttribute("carts",cart.getCartItems());
        return "user/balance";
    }

    private double sum;

}
