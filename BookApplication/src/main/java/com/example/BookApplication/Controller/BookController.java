package com.example.BookApplication.Controller;

import com.example.BookApplication.Entity.Book;
import com.example.BookApplication.Service.BookService;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book/v1")


public class BookController {

    private final BookService bookservice;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @PostMapping("/addBook")
    public ResponseEntity addBook(@RequestBody Book book) {

    }
}



    


