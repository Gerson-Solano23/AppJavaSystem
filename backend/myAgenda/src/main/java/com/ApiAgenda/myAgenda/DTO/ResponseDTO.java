package com.ApiAgenda.myAgenda.DTO;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseDTO {
    public String message;
    public String status;
    public List<Objects> elements;
}
