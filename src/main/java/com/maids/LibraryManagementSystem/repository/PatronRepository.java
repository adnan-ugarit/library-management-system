package com.maids.LibraryManagementSystem.repository;

import com.maids.LibraryManagementSystem.entity.Patron;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Adnan
 */
@Repository
public interface PatronRepository extends JpaRepository<Patron, Long> {

}
