package com.maids.LibraryManagementSystem;

import com.maids.LibraryManagementSystem.api.BookController;
import com.maids.LibraryManagementSystem.entity.Book;
import com.maids.LibraryManagementSystem.service.BookService;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

/**
 *
 * @author Adnan
 */
@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    void testGetBookById_Success() throws Exception {
        Book book = new Book("Test Title", "Test Author", 2021, "123456789", 5);
        when(bookService.findBookById(1L).get()).thenReturn(book);

        mockMvc.perform(get("/api/books/1"))
                .andExpect(status().isOk());

        verify(bookService, times(1)).findBookById(1L);
    }

    @Test
    void testGetBookById_NotFound() throws Exception {
        when(bookService.findBookById(1L)).thenThrow(new IllegalArgumentException("Book not found"));

        mockMvc.perform(get("/api/books/1"))
                .andExpect(status().isNotFound());

        verify(bookService, times(1)).findBookById(1L);
    }
}