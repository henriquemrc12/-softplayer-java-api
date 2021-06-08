package com.challenge.softplayerjavaapi.SoftplayerJavaApi.services;

import com.challenge.softplayerjavaapi.SoftplayerJavaApi.models.BaseModel;

import java.util.List;

public interface IBaseService <T extends BaseModel> {
    T create(T model);
    T update(T model);
    T findById(Long id);
    List<T> findAll();
    boolean deleteById(Long id);
}
