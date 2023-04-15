package com.example.datapenduduk.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class ResponseData<T> {
    private boolean status;
    private List<String> message = new ArrayList<>();
    private T payload;
}
