package com.challenge.softplayerjavaapi.SoftplayerJavaApi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BaseModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="update_at")
    private LocalDateTime updateAt;

    @Column(name="created_at")
    private LocalDateTime createdAt;


}
