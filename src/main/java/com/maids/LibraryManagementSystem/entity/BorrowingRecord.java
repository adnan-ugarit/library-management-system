package com.maids.LibraryManagementSystem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 *
 * @author Adnan
 */
@Entity
public class BorrowingRecord {
    
   @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   
   @ManyToOne
   @JoinColumn(name = "book_id", nullable = false)
   @NotNull(message = "Book is required")
   private Book book;
   
   @ManyToOne
   @JoinColumn(name = "patron_id", nullable = false)
   @NotNull(message = "Patron is required")
   private Patron patron;
   
   @NotNull(message = "Borrow date is required")
   @Column(nullable = false)
   private LocalDate borrowDate;
   
   private LocalDate returnDate;

    public BorrowingRecord() {
    }

    public BorrowingRecord(Book book, Patron patron, LocalDate borrowDate, LocalDate returnDate) {
        this.book = book;
        this.patron = patron;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Patron getPatron() {
        return patron;
    }

    public void setPatron(Patron patron) {
        this.patron = patron;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
   
}
