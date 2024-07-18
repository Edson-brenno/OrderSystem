package com.spring.Service;

import com.spring.Entities.Product;
import com.spring.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public void addProduct(Product product){
        productRepository.save(product);
    }

    public boolean existsProductByName(String name){
        return productRepository.existsProductByName(name);
    }
}
