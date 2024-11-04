package com.maids.LibraryManagementSystem.service;

import com.maids.LibraryManagementSystem.entity.Patron;
import com.maids.LibraryManagementSystem.repository.PatronRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import org.springframework.cache.annotation.Cacheable;

/**
 *
 * @author Adnan
 */
@Service
public class PatronService {

    @Autowired
    private PatronRepository patronRepository;

    @Cacheable("patrons")
    public List<Patron> findAllPatrons() {
        return patronRepository.findAll();
    }

    @Cacheable("patron")
    public Optional<Patron> findPatronById(Long id) {
        return patronRepository.findById(id);
    }

    public Patron savePatron(Patron patron) {
        return patronRepository.save(patron);
    }

    public Optional<Patron> updatePatron(Long id, Patron updatedPatron) {
        return patronRepository.findById(id).map(existingPatron -> {
            existingPatron.setName(updatedPatron.getName());
            existingPatron.setContactInfo(updatedPatron.getContactInfo());
            return patronRepository.save(existingPatron);
        });
    }

    public void deletePatron(Long id) {
        if (patronRepository.existsById(id)) {
            patronRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Patron not found with id: " + id);
        }
    }
}
