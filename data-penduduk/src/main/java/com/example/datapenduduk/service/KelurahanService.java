package com.example.datapenduduk.service;

import com.example.datapenduduk.model.entities.Kelurahan;
import com.example.datapenduduk.repository.KelurahanRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class KelurahanService {
    @Autowired
    private KelurahanRepository kelurahanRepository;

    public Kelurahan save(Kelurahan kelurahan) {
        return kelurahanRepository.save(kelurahan);
    }

    public Iterable<Kelurahan> findAll(Pageable pageable) {
        return kelurahanRepository.findAll(pageable);
    }

    public Kelurahan findById(Long id) {
        Optional<Kelurahan> kelurahan = kelurahanRepository.findById(id);
        if(kelurahan == null) {
            System.out.println("Kelurahan tidak ditemukan");
            return null;
        }
        return kelurahan.get();
    }

    public Iterable<Kelurahan> findByNameContains(String name, Pageable pageable) {
        return kelurahanRepository.findByNameContains(name, pageable);
    }

    public Kelurahan update(Kelurahan kelurahan, Long id) {
        Optional<Kelurahan> kelurahan1 = kelurahanRepository.findById(id);
        if(kelurahan1 == null) {
            System.out.println("Kelurahan tidak ditemukan");
            return null;
        }
        kelurahan1.get().setName(kelurahan.getName());
        kelurahan1.get().setKecamatan(kelurahan.getKecamatan());
        return kelurahanRepository.save(kelurahan1.get());
    }

    public void deleteById(Long id) {
        Optional<Kelurahan> kelurahan = kelurahanRepository.findById(id);
        if(kelurahan == null){
            System.out.println("Kelurahan tidak ditemukan");
        }
        kelurahanRepository.deleteById(id);
    }
}
