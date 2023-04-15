package com.example.datapenduduk.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "kelurahan")
@Setter @Getter
@ToString
public class Kelurahan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false, unique = true)
    private String name;

    @ManyToOne
    private Kecamatan kecamatan;
}
