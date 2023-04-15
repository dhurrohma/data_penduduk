package com.example.datapenduduk.model.dto;

import com.example.datapenduduk.model.entities.Kecamatan;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class KelurahanData {
    @NotEmpty(message = "Name is required")
    private String name;

    private Kecamatan kecamatan;
}
