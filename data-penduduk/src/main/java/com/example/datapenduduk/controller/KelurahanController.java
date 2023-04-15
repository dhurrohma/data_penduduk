package com.example.datapenduduk.controller;

import com.example.datapenduduk.model.dto.KelurahanData;
import com.example.datapenduduk.model.dto.ResponseData;
import com.example.datapenduduk.model.entities.Kelurahan;
import com.example.datapenduduk.service.KelurahanService;
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
@RequestMapping("/kelurahan")
public class KelurahanController {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private KelurahanService kelurahanService;

    @PostMapping
    public ResponseEntity<ResponseData<Kelurahan>> create(@Valid @RequestBody KelurahanData kelurahanData, Errors errors) {
        ResponseData<Kelurahan> responseData = new ResponseData<>();
        if(errors.hasErrors()){
            for (ObjectError error: errors.getAllErrors()){
                responseData.getMessage().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        Kelurahan kelurahan = modelMapper.map(kelurahanData, Kelurahan.class);
        responseData.setStatus(true);
        responseData.setPayload(kelurahanService.save(kelurahan));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/{page}/{sort}")
    public Iterable<Kelurahan> findAll(@PathVariable("page") int page, @PathVariable String sort){
        Pageable pageable = PageRequest.of(page-1, 10, Sort.by("id").ascending());

        if(sort.equalsIgnoreCase("desc")){
            pageable = PageRequest.of(page-1, 10, Sort.by("id").descending());
        }
        return kelurahanService.findAll(pageable);
    }

    @GetMapping("/search/{id}")
    public Kelurahan findById(@PathVariable Long id){
        return kelurahanService.findById(id);
    }

    @GetMapping("/search/name/{page}/{sort}/{name}")
    public Iterable<Kelurahan> findByName(@PathVariable("page") int page, @PathVariable("sort") String sort, @PathVariable("name") String name){
        Pageable pageable = PageRequest.of(page-1, 10, Sort.by("id").ascending());

        if(sort.equalsIgnoreCase("desc")){
            pageable = PageRequest.of(page-1, 10, Sort.by("id").descending());
        }
        return kelurahanService.findByNameContains(name, pageable);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseData<Kelurahan>> update(@Valid @RequestBody KelurahanData kelurahanData, @PathVariable Long id, Errors errors){
        ResponseData<Kelurahan> responseData = new ResponseData<>();
        if (errors.hasErrors()) {
            for(ObjectError err: errors.getAllErrors()) {
                responseData.getMessage().add(err.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        Kelurahan kelurahan = modelMapper.map(kelurahanData, Kelurahan.class);
        responseData.setStatus(true);
        responseData.setPayload(kelurahanService.update(kelurahan, id));
        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        kelurahanService.deleteById(id);
    }
}
