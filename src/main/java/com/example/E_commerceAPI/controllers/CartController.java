package com.example.E_commerceAPI.controllers;

import com.example.E_commerceAPI.models.Cart;
import com.example.E_commerceAPI.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping("/{userId}/add")
    public ResponseEntity<Cart> addToCart(@PathVariable Integer userId, @RequestParam Integer productId){
        return ResponseEntity.ok(cartService.addToCart(userId, productId));
    }
}
