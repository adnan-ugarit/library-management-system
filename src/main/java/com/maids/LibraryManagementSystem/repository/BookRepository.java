package com.maids.LibraryManagementSystem.repository;

import com.maids.LibraryManagementSystem.entity.Book;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Adnan
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByIsbn(String isbn);
}
