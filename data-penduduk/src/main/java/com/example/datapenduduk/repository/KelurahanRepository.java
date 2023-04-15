package com.example.datapenduduk.repository;

import com.example.datapenduduk.model.entities.Kelurahan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface KelurahanRepository extends PagingAndSortingRepository<Kelurahan, Long>, CrudRepository<Kelurahan, Long> {
    Page<Kelurahan> findByNameContains(String name, Pageable pageable);
}
