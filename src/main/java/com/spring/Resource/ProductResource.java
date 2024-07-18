package com.spring.Resource;

import com.spring.Entities.Category;
import com.spring.Entities.Mensage;
import com.spring.Entities.Product;
import com.spring.Service.CategoryService;
import com.spring.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductResource {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok().body(productService.findAll());
    }

    @PostMapping("/new")
    public ResponseEntity<Mensage<Product>> addNewProduct(@RequestParam String name, @RequestParam String description,
                                                          @RequestParam double price, @RequestParam String imgUrl,
                                                           @RequestParam int[] categories){
        Product newProduct = new Product(name, description, price, imgUrl);

        if(categories.length > 0){
            for(int categoryId : categories){
                if(categoryService.existCategoryId(categoryId)){
                    newProduct.getCategories().add(categoryService.findCategoryById(categoryId));
                }
            }
        }

        productService.addProduct(newProduct);
        return ResponseEntity.ok().body(new Mensage<>("Product added successfully", newProduct));
    }
}
