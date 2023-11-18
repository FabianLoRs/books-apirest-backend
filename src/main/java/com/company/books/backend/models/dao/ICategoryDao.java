package com.company.books.backend.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.company.books.backend.models.Category;

public interface ICategoryDao extends CrudRepository<Category, Long> {
    
}
