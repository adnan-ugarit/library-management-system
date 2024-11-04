package com.maids.LibraryManagementSystem.service;

import com.maids.LibraryManagementSystem.entity.Book;
import com.maids.LibraryManagementSystem.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Adnan
 */
@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Cacheable("books")
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    @Cacheable("book")
    public Optional<Book> findBookById(Long id) {
        return bookRepository.findById(id);
    }
    
    public Optional<Book> findBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public Optional<Book> updateBook(Long id, Book updatedBook) {
        return bookRepository.findById(id).map(existingBook -> {
            existingBook.setTitle(updatedBook.getTitle());
            existingBook.setAuthor(updatedBook.getAuthor());
            existingBook.setPublicationYear(updatedBook.getPublicationYear());
            existingBook.setIsbn(updatedBook.getIsbn());
            existingBook.setQuantity(updatedBook.getQuantity());
            return bookRepository.save(existingBook);
        });
    }

    public Optional<Book> updateQuantityBook(Long id, int quantity) {
        return bookRepository.findById(id).map(existingBook -> {
            existingBook.setQuantity(quantity);
            return bookRepository.save(existingBook);
        });
    }

    public boolean checkBookIsAvailable(Long bookId) throws Exception {
        Book book = bookRepository.findById(bookId)
            .orElseThrow(() -> new Exception("Book not found with id " + bookId));
        return book.getQuantity() > 0;
    }

    public void deleteBook(Long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Book not found with id: " + id);
        }
    }
    
    // Update quantity when borrowing a book
    @Transactional
    public void borrowBook(Long id) throws Exception {
        Book book = bookRepository.findById(id)
            .orElseThrow(() -> new Exception("Book not found with id " + id));
        
        if (book.getQuantity() > 0) {
            book.setQuantity(book.getQuantity() - 1);
            bookRepository.save(book);
        } else {
            throw new IllegalArgumentException("Book is not available for borrowing");
        }
    }

    // Update quantity when returning a book
    @Transactional
    public void returnBook(Long id) throws Exception {
        Book book = bookRepository.findById(id)
            .orElseThrow(() -> new Exception("Book not found with id " + id));
        
        book.setQuantity(book.getQuantity() + 1);
        bookRepository.save(book);
    }
    
}
