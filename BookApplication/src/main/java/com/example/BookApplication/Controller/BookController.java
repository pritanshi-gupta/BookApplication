package com.example.BookApplication.Controller;

import com.example.BookApplication.Entity.Book;
import com.example.BookApplication.Service.BookService;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book/v1")

public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/addBook")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {


        return ResponseEntity.ok(bookService.addBook(book));
    }
    @GetMapping("/getBook/{bookName}")
    public ResponseEntity<Book> getBookByName (@PathVariable("bookName") String name){
        bookService.getBookByName(name);
        final Book bookByName = bookService.getBookByName(name);
        return ResponseEntity.ok(bookByName);

    }

    @PutMapping("/updataBook")
    public ResponseEntity<Book> updateBook(@RequestBody Book book){
        Book savedBook = bookService.updateBook(book);
        return ResponseEntity.ok(savedBook);
    }


    @GetMapping("/testing")
    public String testFunction(){
        return "testing string";
    }


}
