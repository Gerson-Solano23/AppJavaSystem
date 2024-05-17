package com.ApiAgenda.myAgenda.DTO;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;
import org.hibernate.persister.collection.mutation.UpdateRowsCoordinatorNoOp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Setter
@Getter
public class ContactDTO {
    private String name;

    private String phone;

    private String email;

    private LocalDate dateOfBirth;
}
