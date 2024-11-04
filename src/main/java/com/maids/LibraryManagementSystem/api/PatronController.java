package com.maids.LibraryManagementSystem.api;

import com.maids.LibraryManagementSystem.entity.Patron;
import com.maids.LibraryManagementSystem.service.PatronService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Adnan
 */
@RestController
@RequestMapping("/api/patrons")
@SecurityRequirement(name = "Bearer Authentication")
public class PatronController {
    
   @Autowired
   private PatronService patronService;

   @GetMapping
   public List<Patron> getAllPatrons() {
       return patronService.findAllPatrons();
   }

   @GetMapping("/{id}")
   public ResponseEntity<Patron> getPatronById(@PathVariable Long id) {
       return patronService.findPatronById(id)
               .map(ResponseEntity::ok)
               .orElse(ResponseEntity.notFound().build());
   }

   @PostMapping
   public ResponseEntity<Patron> addPatron(@Valid @RequestBody Patron patron) {
       Patron savedPatron = patronService.savePatron(patron);
       return ResponseEntity.status(HttpStatus.CREATED).body(savedPatron);
   }

   @PutMapping("/{id}")
   public ResponseEntity<Patron> updatePatron(@PathVariable Long id, @Valid @RequestBody Patron patron) {
       return patronService.updatePatron(id, patron)
               .map(updatedPatron -> ResponseEntity.ok().body(updatedPatron))
               .orElse(ResponseEntity.notFound().build());
   }

   @DeleteMapping("/{id}")
   public ResponseEntity<Void> deletePatron(@PathVariable Long id) {
       patronService.deletePatron(id);
       return ResponseEntity.noContent().build();
   }
   
}
