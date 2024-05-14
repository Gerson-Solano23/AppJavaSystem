package com.ApiAgenda.myAgenda.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Contact")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idContact;

    @NonNull
    @BatchSize(size = 255)
    private String name;

    @NonNull
    @BatchSize(size = 255)
    private String phone;

    @NonNull
    @BatchSize(size = 255)
    private String email;

    @NonNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateRegistry;

    @PrePersist
    public void prePersist() {
        this.dateRegistry = LocalDate.now();
    }
}
/*
    * id -> INT
    * name -> VARCHAR(255)
    * phone -> VARCHAR(255)
    * email -> VARCHAR(255)
    * dateOfBirth -> DATE
    * dateRegistry -> DATETIME
* */