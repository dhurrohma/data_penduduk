package com.example.datapenduduk.controller;

import com.example.datapenduduk.model.dto.KecamatanData;
import com.example.datapenduduk.model.dto.ResponseData;
import com.example.datapenduduk.model.entities.Kecamatan;
import com.example.datapenduduk.service.KecamatanService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kecamatan")
public class KecamatanController {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private KecamatanService kecamatanService;

    @PostMapping
    public ResponseEntity<ResponseData<Kecamatan>> create(@Valid @RequestBody KecamatanData kecamatanData, Errors errors){
        ResponseData<Kecamatan> responseData = new ResponseData<>();
        if(errors.hasErrors()){
            for (ObjectError error: errors.getAllErrors()){
                responseData.getMessage().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        Kecamatan kecamatan = modelMapper.map(kecamatanData, Kecamatan.class);
        responseData.setStatus(true);
        responseData.setPayload(kecamatanService.save(kecamatan));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/{page}/{sort}")
    public Iterable<Kecamatan> findAll(@PathVariable int page, @PathVariable String sort){
        Pageable pageable = PageRequest.of(page-1, 10, Sort.by("id").ascending());

        if(sort.equalsIgnoreCase("desc")){
            pageable = PageRequest.of(page-1, 10, Sort.by("id").descending());
        }
        return kecamatanService.findAll(pageable);
    }

    @GetMapping("/search/{id}")
    public Kecamatan findById(@PathVariable Long id){
        return kecamatanService.findById(id);
    }

    @GetMapping("/search/name/{page}/{sort}/{name}")
    public Iterable<Kecamatan> findByName(@PathVariable("page") int page, @PathVariable String sort, @PathVariable("name") String name){
        Pageable pageable = PageRequest.of(page-1, 10, Sort.by("id").ascending());

        if(sort.equalsIgnoreCase("desc")){
            pageable = PageRequest.of(page-1, 10, Sort.by("id").descending());
        }
        return kecamatanService.findByName(name, pageable);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseData<Kecamatan>> update(@Valid @RequestBody KecamatanData kecamatanData, @PathVariable Long id, Errors errors){
        ResponseData<Kecamatan> responseData = new ResponseData<>();
        if (errors.hasErrors()) {
            for(ObjectError err: errors.getAllErrors()) {
                responseData.getMessage().add(err.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        Kecamatan kecamatan = modelMapper.map(kecamatanData, Kecamatan.class);
        responseData.setStatus(true);
        responseData.setPayload(kecamatanService.update(kecamatan, id));
        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        kecamatanService.deleteById(id);
    }
}
