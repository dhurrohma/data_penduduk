package com.example.datapenduduk.service;

import com.example.datapenduduk.model.entities.Kecamatan;
import com.example.datapenduduk.repository.KecamatanRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class KecamatanService {
    @Autowired
    private KecamatanRepository kecamatanRepository;

    public Kecamatan save(Kecamatan kecamatan) {
        return kecamatanRepository.save(kecamatan);
    }

    public Iterable<Kecamatan> findAll(Pageable pageable) {
        return kecamatanRepository.findAll(pageable);
    }

    public Kecamatan findById(Long id) {
        Optional<Kecamatan> kecamatan = kecamatanRepository.findById(id);
        if(kecamatan == null){
            System.out.println("Kecamatan tidak ditemukan");
            return null;
        }
        return kecamatan.get();
    }

    public Iterable<Kecamatan> findByName(String name, Pageable pageable){
        return kecamatanRepository.findByNameContains(name, pageable);
    }

    public Kecamatan update(Kecamatan kecamatan, Long id) {
        Optional<Kecamatan> kecamatan1 = kecamatanRepository.findById(id);
        if(kecamatan1 == null){
            System.out.println("Kecamatan tidak ditemukan");
            return null;
        }
        kecamatan1.get().setName(kecamatan.getName());
        return kecamatanRepository.save(kecamatan1.get());
    }

    public void deleteById(Long id) {
        Optional<Kecamatan> kecamatan = kecamatanRepository.findById(id);
        if(kecamatan == null){
            System.out.println("Kecamatan tidak ditemukan");
        }
        kecamatanRepository.deleteById(id);
    }
}
