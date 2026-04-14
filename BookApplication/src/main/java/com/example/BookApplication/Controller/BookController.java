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

    // Add book method working fine
    @PostMapping("/addBook")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {


        return ResponseEntity.ok(bookService.addBook(book));
    }
    // Get book find by book name is also working fine
    @GetMapping("/getBook/{bookName}")
    public ResponseEntity<Book> getBookByName (@PathVariable("bookName") String name){
        bookService.getBookByName(name);
        final Book bookByName = bookService.getBookByName(name);
        return ResponseEntity.ok(bookByName);

    }

    // update book method working fine
    @PutMapping("/updateBook")
    public ResponseEntity<Book> updateBook(@RequestBody Book book){
        Book updatedBook = bookService.updateBook(book);
        return ResponseEntity.ok(updatedBook);
    }

    // Delete method working fine
    @DeleteMapping("/deleteBook/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable("id") Integer id){
          bookService.deleteBook(id);
        return ResponseEntity.ok().build();
    }




}
