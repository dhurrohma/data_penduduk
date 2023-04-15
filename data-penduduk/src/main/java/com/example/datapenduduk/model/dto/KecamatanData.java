package com.example.datapenduduk.model.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class KecamatanData {
    @NotEmpty(message = "Name is required")
    private String name;
}
