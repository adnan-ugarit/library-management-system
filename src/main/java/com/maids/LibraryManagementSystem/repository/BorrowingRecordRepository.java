package com.maids.LibraryManagementSystem.repository;

import com.maids.LibraryManagementSystem.entity.Book;
import com.maids.LibraryManagementSystem.entity.BorrowingRecord;
import com.maids.LibraryManagementSystem.entity.Patron;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Adnan
 */
@Repository
public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord, Long> {
   Optional<BorrowingRecord> findByBookAndPatron(Book book, Patron patron);
}
