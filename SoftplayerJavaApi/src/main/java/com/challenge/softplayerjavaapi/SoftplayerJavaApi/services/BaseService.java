package com.challenge.softplayerjavaapi.SoftplayerJavaApi.services;


import com.challenge.softplayerjavaapi.SoftplayerJavaApi.exception.ApiException;
import com.challenge.softplayerjavaapi.SoftplayerJavaApi.models.BaseModel;
import com.challenge.softplayerjavaapi.SoftplayerJavaApi.repositories.BaseRepository;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public abstract class BaseService<T extends BaseModel> implements IBaseService<T>{

    public static final Logger logger = LoggerFactory.getLogger(BaseService.class);

    @Autowired
    private BaseRepository<T> repository;

    @Override
    public T create(T model) {
        try {
            model.setId(null);
            model.setCreatedAt(LocalDateTime.now());
            model.setUpdateAt(LocalDateTime.now());
            return repository.save(model);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error", e);
            throw new ApiException("Erro ao salvar o " + model.toString());
        }
    }


    @Override
    public T update(T model) {
        try {
            T modelUpdate = repository.findById(model.getId())
                    .orElseThrow(() -> new NotFoundException("Unable to get " + model.getClass().getName() + model.getClass()));
            model.setUpdateAt(LocalDateTime.now());
            return repository.save(model);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error", e);
            throw new ApiException("Erro ao atualizar o " + model.getClass().getName());
        }
    }

    @Override
    public T findById(Long id) {
        try {
            return repository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Unable to get " + id ));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error", e);
            throw new ApiException("Erro ao buscar o objeto do id " + id);
        }
    }

    @Override
    public List<T> findAll() {
        try {
            return repository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error", e);
            throw new ApiException("Erro ao buscar os dados.");
        }
    }

    @Override
    public boolean deleteById(Long id) {
        try {
            T modelUpdate = repository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Unable to get " + id));
            repository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error", e);
            throw new ApiException("Erro ao deletar o objeto do id " + id);
        }
    }

}
