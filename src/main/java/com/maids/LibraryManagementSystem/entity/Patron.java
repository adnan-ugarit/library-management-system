package com.maids.LibraryManagementSystem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

/**
 *
 * @author Adnan
 */
@Entity
public class Patron {
    
   @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   
   @NotBlank(message = "Name is required")
   @Column(nullable = false)
   private String name;
   
   private String contactInfo;

    public Patron() {
    }

    public Patron(String name, String contactInfo) {
        this.name = name;
        this.contactInfo = contactInfo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

}
