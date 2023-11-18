package com.company.books.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.books.backend.models.Category;
import com.company.books.backend.response.CategoryResponseRest;
import com.company.books.backend.services.ICategoryService;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/v1")
public class CategoryRestController {

    @Autowired
    private ICategoryService service;

    @GetMapping("/categories")
    public ResponseEntity<CategoryResponseRest> queryCategory() {
        ResponseEntity<CategoryResponseRest> response = service.findCategories();
        return response;

    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<CategoryResponseRest> queryById(@PathVariable Long id) {
        ResponseEntity<CategoryResponseRest> response = service.findById(id);
        return response;

    }

    @PostMapping("/categories")
    public ResponseEntity<CategoryResponseRest> create(@RequestBody Category request) {
        ResponseEntity<CategoryResponseRest> response = service.createCategory(request);
        return response;

    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<CategoryResponseRest> update(@RequestBody Category request, @PathVariable Long id) {
        ResponseEntity<CategoryResponseRest> response = service.updateCategory(request, id);
        return response;

    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<CategoryResponseRest> delete(@PathVariable Long id) {
        ResponseEntity<CategoryResponseRest> response = service.deleteCategory(id);
        return response;

    }
}
