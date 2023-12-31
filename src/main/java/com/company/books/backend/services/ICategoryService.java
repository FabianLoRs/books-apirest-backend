package com.company.books.backend.services;

import org.springframework.http.ResponseEntity;

import com.company.books.backend.models.Category;
import com.company.books.backend.response.CategoryResponseRest;

public interface ICategoryService {
    
    public ResponseEntity<CategoryResponseRest> findCategories();
    public  ResponseEntity<CategoryResponseRest> findById(Long id);
    public ResponseEntity<CategoryResponseRest> createCategory(Category category);
    public ResponseEntity<CategoryResponseRest> updateCategory(Category category, Long id);
    public ResponseEntity<CategoryResponseRest> deleteCategory(Long id);
    
}
