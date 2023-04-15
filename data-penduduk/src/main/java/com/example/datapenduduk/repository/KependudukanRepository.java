package com.example.datapenduduk.repository;

import com.example.datapenduduk.model.entities.Kependudukan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface KependudukanRepository extends PagingAndSortingRepository<Kependudukan, Long>, CrudRepository<Kependudukan, Long> {
    Page<Kependudukan> findByNameContains(String name, Pageable pageable);
    Page<Kependudukan> findByNikContains(String nik, Pageable pageable);
}
