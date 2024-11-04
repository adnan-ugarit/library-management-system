package com.maids.LibraryManagementSystem;

import com.maids.LibraryManagementSystem.entity.Book;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import com.maids.LibraryManagementSystem.repository.BookRepository;
import com.maids.LibraryManagementSystem.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

/**
 *
 * @author Adnan
 */
public class BookServiceTest {
    
    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    void testFindById_Success() {
        Book book = new Book("Test Title", "Test Author", 2021, "123456789", 5);
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        Book foundBook = bookService.findBookById(1L).get();

        assertEquals("Test Title", foundBook.getTitle());
        verify(bookRepository, times(1)).findById(1L);
    }

    @Test
    void testFindById_NotFound() {
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> bookService.findBookById(1L));
        verify(bookRepository, times(1)).findById(1L);
    }
    
}
