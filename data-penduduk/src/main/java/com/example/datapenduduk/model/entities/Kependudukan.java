package com.example.datapenduduk.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@Table(name = "kependudukan")
@Getter @Setter
@ToString
public class Kependudukan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 16, nullable = false, unique = true)
    private String nik;

    @Column(length = 250, nullable = false)
    private String name;

    @Column(length = 100, nullable = false)
    private String tanggalLahir;

    @Column(length = 100, nullable = false)
    private String jenisKelamin;

    @Column(length = 100, nullable = false)
    private String agama;

    @Column(length = 100, nullable = false)
    private String status;

    @Column(length = 100, nullable = false)
    private String pekerjaan;

    @Column(length = 100, nullable = false)
    private String kewarganegaraan;

    @ManyToOne
    private Kelurahan kelurahan;
}
