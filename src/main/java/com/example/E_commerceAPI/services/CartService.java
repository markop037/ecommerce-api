package com.example.E_commerceAPI.services;

import com.example.E_commerceAPI.models.Cart;
import com.example.E_commerceAPI.models.Product;
import com.example.E_commerceAPI.models.User;
import com.example.E_commerceAPI.repositories.CartRepository;
import com.example.E_commerceAPI.repositories.ProductRepository;
import com.example.E_commerceAPI.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;


    public Cart addToCart(Integer userId, Integer productId){
        User user = userRepository.findById(userId).orElseThrow(()
                -> new RuntimeException("User not found"));
        Product product = productRepository.findById(productId).orElseThrow(()
                -> new RuntimeException("Product not found"));
        Cart cart = cartRepository.findByUserId(userId).orElse(new Cart());
        cart.setUser(user);

        if(cart.getProducts() == null){
            cart.setProducts(new ArrayList<>());
        }

        cart.getProducts().add(product);

        double newTotalPrice = cart.getProducts().stream().mapToDouble(Product :: getPrice).sum();
        cart.setTotalPrice(newTotalPrice);

        return cartRepository.save(cart);
    }

    public Cart getCart(Integer userId){
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        return cartRepository.findByUserId(userId).orElseThrow(() -> new RuntimeException("Cart not found"));
    }

}
