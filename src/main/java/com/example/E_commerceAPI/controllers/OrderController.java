package com.example.E_commerceAPI.controllers;

import com.example.E_commerceAPI.models.Order;
import com.example.E_commerceAPI.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/{userId}")
    public ResponseEntity<Order> placeOrder(@PathVariable Integer userId, @RequestBody List<Integer> productIds){
        return ResponseEntity.ok(orderService.placeOrder(userId, productIds));
    }
}
