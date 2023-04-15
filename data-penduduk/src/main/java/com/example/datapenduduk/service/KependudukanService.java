package com.example.datapenduduk.service;

import com.example.datapenduduk.model.entities.Kependudukan;
import com.example.datapenduduk.repository.KependudukanRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class KependudukanService {
    @Autowired
    private KependudukanRepository kependudukanRepository;

    public Kependudukan save(Kependudukan kependudukan) {
        return kependudukanRepository.save(kependudukan);
    }

    public Iterable<Kependudukan> findAll(Pageable pageable) {
        return kependudukanRepository.findAll(pageable);
    }

    public Kependudukan findById(Long id) {
        Optional<Kependudukan> kependudukan = kependudukanRepository.findById(id);
        if(kependudukan == null){
            System.out.println("Penduduk tidak ditemukan");
            return null;
        }
        return kependudukan.get();
    }

    public Iterable<Kependudukan> findByNameContains(String name, Pageable pageable){
        return kependudukanRepository.findByNameContains(name, pageable);
    }

    public Iterable<Kependudukan> findByNikContains(String nik, Pageable pageable){
        return kependudukanRepository.findByNikContains(nik, pageable);
    }

    public Kependudukan update(Kependudukan kependudukan, Long id){
        Optional<Kependudukan> kependudukan1 = kependudukanRepository.findById(id);
        if(kependudukan1 == null){
            System.out.println("Penduduk tidak ditemukan");
            return null;
        }
        kependudukan1.get().setNik(kependudukan.getNik());
        kependudukan1.get().setName(kependudukan.getName());
        kependudukan1.get().setTanggalLahir(kependudukan.getTanggalLahir());
        kependudukan1.get().setJenisKelamin(kependudukan.getJenisKelamin());
        kependudukan1.get().setAgama(kependudukan.getAgama());
        kependudukan1.get().setStatus(kependudukan.getStatus());
        kependudukan1.get().setPekerjaan(kependudukan.getPekerjaan());
        kependudukan1.get().setKewarganegaraan(kependudukan.getKewarganegaraan());
        kependudukan1.get().setKelurahan(kependudukan.getKelurahan());
        return kependudukanRepository.save(kependudukan1.get());
    }

    public void deleteById(Long id) {
        Optional<Kependudukan> kependudukan = kependudukanRepository.findById(id);
        if(kependudukan == null){
            System.out.println("Kependudukan tidak ditemukan");
        }
        kependudukanRepository.deleteById(id);
    }
}
