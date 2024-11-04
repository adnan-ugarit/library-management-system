package com.maids.LibraryManagementSystem.api;

import com.maids.LibraryManagementSystem.service.BorrowingService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Adnan
 */
@RestController
@RequestMapping("/api")
@SecurityRequirement(name = "Bearer Authentication")
public class BorrowingController {
   
   @Autowired
   private BorrowingService borrowingService;

   @PostMapping("/borrow/{bookId}/patron/{patronId}")
   public ResponseEntity<String> borrowBook(@PathVariable Long bookId, @PathVariable Long patronId) {
       String msg = "Success Operation, borrowing is completed.";
       try {
           borrowingService.borrowBook(bookId, patronId);
       }
       catch(Exception ex) {
           msg = ex.getMessage();
       }
       return ResponseEntity.ok().body(msg);
   }

   @PutMapping("/return/{bookId}/patron/{patronId}")
   public ResponseEntity<String> returnBook(@PathVariable Long bookId, @PathVariable Long patronId) {
       String msg = "Success Operation, returning the book is completed.";
       try {
           borrowingService.returnBook(bookId, patronId);
       }
       catch(Exception ex) {
           msg = ex.getMessage();
       }
       return ResponseEntity.ok().body(msg);
   }
   
}
