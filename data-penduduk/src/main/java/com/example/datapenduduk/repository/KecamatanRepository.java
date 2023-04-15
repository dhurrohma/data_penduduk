package com.example.datapenduduk.repository;

import com.example.datapenduduk.model.entities.Kecamatan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface KecamatanRepository extends PagingAndSortingRepository<Kecamatan, Long>, CrudRepository<Kecamatan, Long> {
    Page<Kecamatan> findByNameContains(String name, Pageable pageable);
}
