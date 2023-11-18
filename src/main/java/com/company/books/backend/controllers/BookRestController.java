package com.company.books.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.books.backend.models.Book;
import com.company.books.backend.response.BookResponseRest;
import com.company.books.backend.services.IBookService;

@RestController
@RequestMapping("/v1")
public class BookRestController {

    @Autowired
    private IBookService service;

    @GetMapping("/books")
    public ResponseEntity<BookResponseRest> queryBooks() {
        ResponseEntity<BookResponseRest> response = service.findBooks();
        return response;

    }

    @GetMapping("/books/{id}")
    public ResponseEntity<BookResponseRest> queryById(@PathVariable Long id) {
        ResponseEntity<BookResponseRest> response = service.findById(id);
        return response;

    }

    @PostMapping("/books")
    public ResponseEntity<BookResponseRest> createBook(@RequestBody Book request) {
        ResponseEntity<BookResponseRest> response = service.saveBook(request);
        return response;

    }

    @PutMapping("/books/{id}")
    public ResponseEntity<BookResponseRest> updateBook(@RequestBody Book request, @PathVariable Long id) {
        ResponseEntity<BookResponseRest> response = service.updateBook(request, id);
        return response;

    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<BookResponseRest> deleteBook(@PathVariable Long id) {
        ResponseEntity<BookResponseRest> response = service.deleteBook(id);
        return response;

    }

}
