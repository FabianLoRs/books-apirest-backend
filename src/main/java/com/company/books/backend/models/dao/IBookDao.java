package com.company.books.backend.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.company.books.backend.models.Book;

public interface IBookDao extends CrudRepository<Book, Long>{

} 