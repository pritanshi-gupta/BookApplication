package com.example.BookApplication.Service;

import com.example.BookApplication.Entity.Book;
import com.example.BookApplication.Repository.BookRepository;
import com.example.BookApplication.Exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    //  Add Book
    public Book addBook(Book book){
        return bookRepository.save(book);
    }

    //  Get Book by Name (with exception handling)
    public Book getBookByName(String name) {
        Book book = bookRepository.findBookByTitle(name);

        if (book == null) {
            throw new ResourceNotFoundException("Book not found with name: " + name);
        }

        return book;
    }

    // 🔄 Update Book
    public Book updateBook(Book book) {
        return bookRepository.save(book);
    }

    //  Delete Book (with exception handling)
    public void deleteBook(Integer id) {

        if (!bookRepository.existsById(id)) {
            throw new ResourceNotFoundException("Book not found with id: " + id);
        }

        bookRepository.deleteById(id);
    }

    //Get Book by ID
    public Book getBookById(Integer id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
    }
}
