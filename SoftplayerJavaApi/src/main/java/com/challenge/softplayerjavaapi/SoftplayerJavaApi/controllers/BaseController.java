package com.challenge.softplayerjavaapi.SoftplayerJavaApi.controllers;


import com.challenge.softplayerjavaapi.SoftplayerJavaApi.models.BaseModel;
import com.challenge.softplayerjavaapi.SoftplayerJavaApi.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public abstract class BaseController<T extends BaseModel> implements IBaseController<T> {

    @Autowired
    protected BaseService<T> service;

    @Override
    @PostMapping
    public ResponseEntity<T> create(@Valid @RequestBody T model) {
        return ResponseEntity.ok().body(service.create(model));
    }

    @Override
    @PutMapping
    public ResponseEntity<T> update(@Valid @RequestBody T model) {
        return ResponseEntity.ok().body(service.update(model));
    }

    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity<T> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    };

    @Override
    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    };

    @Override
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.deleteById(id));

    }

}

