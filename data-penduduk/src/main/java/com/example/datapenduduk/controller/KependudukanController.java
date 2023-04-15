package com.example.datapenduduk.controller;

import com.example.datapenduduk.model.dto.KependudukanData;
import com.example.datapenduduk.model.dto.ResponseData;
import com.example.datapenduduk.model.entities.Kependudukan;
import com.example.datapenduduk.service.KependudukanService;
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
@RequestMapping("/kependudukan")
public class KependudukanController {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private KependudukanService kependudukanService;

    @PostMapping
    public ResponseEntity<ResponseData<Kependudukan>> create(@Valid @RequestBody KependudukanData kependudukanData, Errors errors){
        ResponseData<Kependudukan> responseData = new ResponseData<>();
        if(errors.hasErrors()){
            for (ObjectError error: errors.getAllErrors()){
                responseData.getMessage().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        Kependudukan kependudukan = modelMapper.map(kependudukanData, Kependudukan.class);
        responseData.setStatus(true);
        responseData.setPayload(kependudukanService.save(kependudukan));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/{page}/{sort}")
    public Iterable<Kependudukan> findAll(@PathVariable("page") int page, @PathVariable("sort") String sort){
        Pageable pageable = PageRequest.of(page-1, 10, Sort.by("id").ascending());

        if(sort.equalsIgnoreCase("desc")){
            pageable = PageRequest.of(page-1, 10, Sort.by("id").descending());
        }
        return kependudukanService.findAll(pageable);
    }

    @GetMapping("/search/{id}")
    public Kependudukan findById(@PathVariable Long id){
        return kependudukanService.findById(id);
    }

    @GetMapping("/search/name/{page}/{sort}/{name}")
    public Iterable<Kependudukan> findByName(@PathVariable("page") int page,@PathVariable("sort") String sort, @PathVariable("name") String name){
        Pageable pageable = PageRequest.of(page-1, 10, Sort.by("id").ascending());

        if(sort.equalsIgnoreCase("desc")){
            pageable = PageRequest.of(page-1, 10, Sort.by("id").descending());
        }
        return kependudukanService.findByNameContains(name, pageable);
    }

    @GetMapping("/search/nik/{page}/{sort}/{nik}")
    public Iterable<Kependudukan> findByNik(@PathVariable("page") int page,@PathVariable("sort") String sort, @PathVariable("nik") String nik){
        Pageable pageable = PageRequest.of(page-1, 10, Sort.by("id").ascending());

        if(sort.equalsIgnoreCase("desc")){
            pageable = PageRequest.of(page-1, 10, Sort.by("id").descending());
        }
        return kependudukanService.findByNikContains(nik, pageable);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseData<Kependudukan>> update(@Valid @RequestBody KependudukanData kependudukanData, @PathVariable Long id, Errors errors){
        ResponseData<Kependudukan> responseData = new ResponseData<>();
        if (errors.hasErrors()) {
            for(ObjectError err: errors.getAllErrors()) {
                responseData.getMessage().add(err.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        Kependudukan kependudukan = modelMapper.map(kependudukanData, Kependudukan.class);
        responseData.setStatus(true);
        responseData.setPayload(kependudukanService.update(kependudukan, id));
        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        kependudukanService.deleteById(id);
    }
}
