package com.example.E_commerceAPI.services;

import com.example.E_commerceAPI.models.Product;
import com.example.E_commerceAPI.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Product addProduct(Product product){
        return productRepository.save(product);
    }

    public Optional<Product> findProductById(Integer id){
        return productRepository.findById(id);
    }

    public void deleteProduct(Integer id){
        productRepository.deleteById(id);
    }
}
