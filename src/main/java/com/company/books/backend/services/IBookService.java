package com.company.books.backend.services;

import org.springframework.http.ResponseEntity;

import com.company.books.backend.models.Book;
import com.company.books.backend.response.BookResponseRest;

public interface IBookService {
    public ResponseEntity<BookResponseRest> findBooks();
    public ResponseEntity<BookResponseRest> findById(Long id);
    public ResponseEntity<BookResponseRest> saveBook(Book book);
    public ResponseEntity<BookResponseRest> updateBook (Book book, Long id);
    public ResponseEntity<BookResponseRest> deleteBook(Long id);

}
