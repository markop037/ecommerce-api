package com.example.E_commerceAPI.services;

import com.example.E_commerceAPI.models.Order;
import com.example.E_commerceAPI.models.Product;
import com.example.E_commerceAPI.models.User;
import com.example.E_commerceAPI.repositories.OrderRepository;
import com.example.E_commerceAPI.repositories.ProductRepository;
import com.example.E_commerceAPI.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;

    public Order placeOrder(Integer userId, List<Integer> productIds){
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        List<Product> products = productRepository.findAllById(productIds);

        if(products.size() != productIds.size()){
            throw new RuntimeException("One or more products not found");
        }

        Order order = new Order();
        order.setUser(user);
        order.setProducts(products);

        double totalPrice = products.stream().mapToDouble(Product :: getPrice).sum();
        order.setTotalPrice(totalPrice);

        order.setStatus("PENDING");
        order.setOrderDate(LocalDateTime.now());

        return orderRepository.save(order);
    }

    public List<Order> getUserOrders(Integer userId){
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return orderRepository.findAllByUserId(userId);
    }
}
