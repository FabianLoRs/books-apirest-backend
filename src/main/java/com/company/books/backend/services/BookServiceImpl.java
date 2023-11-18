package com.company.books.backend.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.books.backend.models.Book;
import com.company.books.backend.models.dao.IBookDao;
import com.company.books.backend.response.BookResponseRest;

@Service
public class BookServiceImpl implements IBookService {

    private static final Logger log = LoggerFactory.getLogger(BookServiceImpl.class);

    @Autowired
    private IBookDao bookDao;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<BookResponseRest> findBooks() {

        log.info("Start getBooks() method");
        BookResponseRest response = new BookResponseRest();

        try {
            List<Book> books = (List<Book>) bookDao.findAll();
            response.getBookResponse().setBook(books);
            response.setMetadata("OK", "00", "Successful Response.");


        } catch (Exception ex) {
            log.error("Error while find books: ", ex.getMessage());
            ex.getStackTrace();
            response.setMetadata("Fail", "-11", "Error while find books.");
            return new ResponseEntity<BookResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        return new ResponseEntity<BookResponseRest>(response, HttpStatus.OK);

    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<BookResponseRest> findById(Long id) {

        log.info("Start findById() method.");
        BookResponseRest response = new BookResponseRest();
        List<Book> listBooks = new ArrayList<>();

        try {

            Optional<Book> book = bookDao.findById(id);

            if (book.isPresent()) {
                listBooks.add(book.get());
                response.getBookResponse().setBook(listBooks);

            } else {
                log.error("Error to get book.");
                response.setMetadata("Fail", "-11", "Book not found.");
                return new ResponseEntity<BookResponseRest>(response, HttpStatus.NOT_FOUND);

            }
        } catch (Exception ex) {
            log.error("Error to get book.", ex.getMessage());
            ex.getStackTrace();
            response.setMetadata("Fail", "-11", "Error to get book.");
            return new ResponseEntity<BookResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }
        response.setMetadata("OK", "00", "Successful Response.");
        return new ResponseEntity<BookResponseRest>(response, HttpStatus.OK);

    }

    @Override
    @Transactional
    public ResponseEntity<BookResponseRest> saveBook(Book book) {

        log.info("Start createBook() method.");
        BookResponseRest response = new BookResponseRest();
        List<Book> listBooks = new ArrayList<>();

        try {

            Book saveBook = bookDao.save(book);
            if (saveBook != null) {
                listBooks.add(saveBook);
                response.getBookResponse().setBook(listBooks);

            } else {
                log.error("Error to save Book.");
                response.setMetadata("Fail", "-11", "Book not created.");
                return new ResponseEntity<BookResponseRest>(response, HttpStatus.BAD_REQUEST);

            }
        } catch (Exception ex) {
            log.error("Error to save Book.", ex.getMessage());
            ex.getStackTrace();
            response.setMetadata("Fail", "-11", "Error to create Book.");
            return new ResponseEntity<BookResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }
        response.setMetadata("OK", "00", "Successful Response.");
        return new ResponseEntity<BookResponseRest>(response, HttpStatus.OK);

    }

    @Override
    @Transactional
    public ResponseEntity<BookResponseRest> updateBook (Book book, Long id) {
        
        log.info("Start uptadeBook() method.");
        BookResponseRest response = new BookResponseRest();
        List<Book> listBooks = new ArrayList<>();

        try {

            Optional<Book> searchedBook = bookDao.findById(id);

            if (searchedBook.isPresent()) {
                searchedBook.get().setName(book.getName());                
                searchedBook.get().setDescription(book.getDescription());
                searchedBook.get().setBookCategory(book.getBookCategory());   
                
                Book updateBook = bookDao.save(searchedBook.get());

                if (updateBook != null) {
                    response.setMetadata("OK", "00", "Successful updated book.");
                    listBooks.add(updateBook);
                    response.getBookResponse().setBook(listBooks);
                    
                } else {
                    log.error("Error to update book");
                    response.setMetadata("Fail", "-11", "Error to update book.");
                    return new ResponseEntity<BookResponseRest>(response, HttpStatus.BAD_REQUEST);

                }

            } else {
                log.error("Error to update book");
                response.setMetadata("Fail", "-11", "Error to update book.");
                return new ResponseEntity<BookResponseRest>(response, HttpStatus.NOT_FOUND); 
                
            }
            
        } catch (Exception ex) {
            log.error("Error to update book", ex.getMessage());
            ex.getStackTrace();
            response.setMetadata("Fail", "-11", "Error to update book.");
            return new ResponseEntity<BookResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        return new ResponseEntity<BookResponseRest>(response, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<BookResponseRest> deleteBook(Long id) {

        log.info("Start deleteBook() method.");
        BookResponseRest response = new BookResponseRest();

        try {
            bookDao.deleteById(id);
            response.setMetadata("OK", "00", "Successful deleted book.");

        } catch (Exception ex) {
            log.error("Error to delete book", ex.getMessage());
            ex.getStackTrace();
            response.setMetadata("Fail", "-11", "Error to delete book");
            return new ResponseEntity<BookResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            
        }

        return new ResponseEntity<BookResponseRest>(response, HttpStatus.OK);
        
    }

}
