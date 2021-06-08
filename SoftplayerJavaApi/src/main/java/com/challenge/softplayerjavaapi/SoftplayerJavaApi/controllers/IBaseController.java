package com.challenge.softplayerjavaapi.SoftplayerJavaApi.controllers;

import com.challenge.softplayerjavaapi.SoftplayerJavaApi.models.BaseModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


public interface IBaseController<T extends BaseModel> {

    ResponseEntity<T> create(T model);
    ResponseEntity<T> update(T model);

    @GetMapping(value = "/{id}")
    ResponseEntity<T> findById(@PathVariable Long id);

    ResponseEntity<?> findAll();

    @DeleteMapping(value = "/{id}")
    ResponseEntity<?> deleteById(@PathVariable Long id);
}
