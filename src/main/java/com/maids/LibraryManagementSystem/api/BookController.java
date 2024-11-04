package com.maids.LibraryManagementSystem.api;

import com.maids.LibraryManagementSystem.entity.Book;
import com.maids.LibraryManagementSystem.service.BookService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Adnan
 */
@RestController
@RequestMapping("/api/books")
@SecurityRequirement(name = "Bearer Authentication")
public class BookController {
    
   @Autowired
   private BookService bookService;

   @GetMapping
   public List<Book> getAllBooks() {
       return bookService.findAllBooks();
   }

   @GetMapping("/{id}")
   public ResponseEntity<Book> getBookById(@PathVariable Long id) {
       return bookService.findBookById(id)
               .map(ResponseEntity::ok)
               .orElse(ResponseEntity.notFound().build());
   }

   @PostMapping
   public ResponseEntity<Book> addBook(@Valid @RequestBody Book book) {
       Book savedBook = bookService.saveBook(book);
       return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
   }

   @PutMapping("/{id}")
   public ResponseEntity<Book> updateBook(@PathVariable Long id, @Valid @RequestBody Book book) {
       return bookService.updateBook(id, book)
               .map(updatedBook -> ResponseEntity.ok().body(updatedBook))
               .orElse(ResponseEntity.notFound().build());
   }
   
   @PutMapping("/{id}/update-quantity")
   public ResponseEntity<Book> updateQuantityBook(@PathVariable Long id, Integer quantity) {
       return bookService.updateQuantityBook(id, quantity)
               .map(updatedBook -> ResponseEntity.ok().body(updatedBook))
               .orElse(ResponseEntity.notFound().build());
   }

   @DeleteMapping("/{id}")
   public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
       bookService.deleteBook(id);
       return ResponseEntity.noContent().build();
   }

   @GetMapping("/{id}/isAvailable")
   public ResponseEntity<String> checkBookIsAvailable(@PathVariable Long id) {
       String msg = "";
       try {
           if (bookService.checkBookIsAvailable(id))
               return ResponseEntity.ok().body("Book with id=" + id + " is Available.");
           else
               return ResponseEntity.ok().body("Book with id=" + id + " isn't Available.");
       }
       catch(Exception ex) {
           msg = ex.getMessage();
       }
       return ResponseEntity.ok().body(msg);
   }
   
   @GetMapping("/isbn/{isbn}")
    public ResponseEntity<Book> getBookByIsbn(@PathVariable String isbn) {
        return bookService.findBookByIsbn(isbn)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
   
}
