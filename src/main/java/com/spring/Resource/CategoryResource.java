package com.spring.Resource;

import com.spring.Entities.Category;
import com.spring.Entities.Mensage;
import com.spring.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryResource {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> getCategories() {
        return ResponseEntity.ok(categoryService.findAll());
    }

    @GetMapping(value = "/filter/name/{name}")
    public ResponseEntity<List<Category>> getCategoriesByName(@PathVariable String name) {
        return ResponseEntity.ok().body(categoryService.findByCategoryName(name));
    }

    @PostMapping(value = "/new")
    public ResponseEntity<Mensage<Category>> addCategory(@RequestParam String name) {
        try{
            Category category = new Category(name.toLowerCase());
            if(!categoryService.existCategoryName(name.toLowerCase())){
                categoryService.newCategory(category);
                return ResponseEntity.ok().body(new Mensage<Category>("Category added successfully", category));
            }else{
                return ResponseEntity.ok().body(new Mensage<Category>("Category already exists", category));
            }

        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
