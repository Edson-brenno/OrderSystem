package com.spring.Service;

import com.spring.Entities.Category;
import com.spring.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public void newCategory(Category category) {
        categoryRepository.save(category);
    }

    public List<Category> findByCategoryName(String categoryName) {
        return categoryRepository.getCategoriesByName(categoryName);
    }

    public boolean existCategoryName(String categoryName) {
        return categoryRepository.existsByName(categoryName);
    }

    public boolean existCategoryId(int categoryId){
        return categoryRepository.existsById(categoryId);
    }

    public Category findCategoryById(int categoryId) {
        return categoryRepository.findById(categoryId);
    }

}
