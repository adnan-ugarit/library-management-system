package com.maids.LibraryManagementSystem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 *
 * @author Adnan
 */
@Entity
public class Book {
    
   @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   
   @NotBlank(message = "Title is required")
   @Column(nullable = false)
   private String title;
   
   @NotBlank(message = "Author is required")
   @Column(nullable = false)
   private String author;
   
   @NotNull(message = "Publication year is required")
   @Column(nullable = false)
   private Integer publicationYear;
   
   @NotBlank(message = "ISBN is required")
   @Column(unique = true, nullable = false)
   private String isbn;
   
   @NotNull(message = "Quantity is required")
   @Min(value = 0, message = "Quantity cannot be negative")
   @Column(nullable = false)
   private Integer quantity;

    public Book() {
    }

    public Book(String title, String author, Integer publicationYear, String isbn, Integer quantity) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.isbn = isbn;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Integer publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}
