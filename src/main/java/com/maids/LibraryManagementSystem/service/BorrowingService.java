package com.maids.LibraryManagementSystem.service;

import com.maids.LibraryManagementSystem.entity.Book;
import com.maids.LibraryManagementSystem.entity.BorrowingRecord;
import com.maids.LibraryManagementSystem.entity.Patron;
import com.maids.LibraryManagementSystem.repository.BookRepository;
import com.maids.LibraryManagementSystem.repository.BorrowingRecordRepository;
import com.maids.LibraryManagementSystem.repository.PatronRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

/**
 *
 * @author Adnan
 */
@Service
public class BorrowingService {

    @Autowired
    private BookService bookService;
    
    @Autowired
    private PatronService patronService;
    
    /*@Autowired
    private BookRepository bookRepository;

    @Autowired
    private PatronRepository patronRepository;*/

    @Autowired
    private BorrowingRecordRepository borrowingRecordRepository;

    public void borrowBook(Long bookId, Long patronId) throws Exception {
        Book book = bookService.findBookById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id: " + bookId));
        Patron patron = patronService.findPatronById(patronId)
                .orElseThrow(() -> new EntityNotFoundException("Patron not found with id: " + patronId));

        // Check if the book is available for borrowing
        if (bookService.checkBookIsAvailable(bookId)) {
            bookService.borrowBook(bookId);
        } else {
            throw new IllegalStateException("Book is not available for borrowing");
        }

        // Create a new borrowing record
        BorrowingRecord borrowingRecord = new BorrowingRecord();
        borrowingRecord.setBook(book);
        borrowingRecord.setPatron(patron);
        borrowingRecord.setBorrowDate(LocalDate.now());
        borrowingRecordRepository.save(borrowingRecord);
    }

    public void returnBook(Long bookId, Long patronId) throws Exception {
        Book book = bookService.findBookById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id: " + bookId));
        Patron patron = patronService.findPatronById(patronId)
                .orElseThrow(() -> new EntityNotFoundException("Patron not found with id: " + patronId));

        BorrowingRecord borrowingRecord = borrowingRecordRepository
                .findByBookAndPatron(book, patron)
                .orElseThrow(() -> new IllegalStateException("No borrowing record found for this book and patron."));

        // Update the return date
        borrowingRecord.setReturnDate(LocalDate.now());
        bookService.returnBook(bookId);
        borrowingRecordRepository.save(borrowingRecord);
    }
}
