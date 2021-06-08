package com.challenge.softplayerjavaapi.SoftplayerJavaApi.repositories;


import com.challenge.softplayerjavaapi.SoftplayerJavaApi.models.BaseModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseRepository<T extends BaseModel> extends JpaRepository<T, Long> { }
