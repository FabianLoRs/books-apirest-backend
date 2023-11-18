package com.company.books.backend.response;

import java.util.List;

import com.company.books.backend.models.Book;

public class BookResponse {
    
    private List<Book> books;

    public List<Book> getBook() {
        return books;
    }

    public void setBook(List<Book> books) {
        this.books = books;
    }
}
