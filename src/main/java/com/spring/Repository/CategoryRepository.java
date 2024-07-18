package com.spring.Repository;

import com.spring.Entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    List<Category> getCategoriesByName(String name);

    boolean existsByName(String name);
    boolean existsById(int id);
    Category findById(int id);
}
