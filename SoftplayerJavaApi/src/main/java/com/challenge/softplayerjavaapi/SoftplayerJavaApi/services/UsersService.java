package com.challenge.softplayerjavaapi.SoftplayerJavaApi.services;

import com.challenge.softplayerjavaapi.SoftplayerJavaApi.exception.ApiException;
import com.challenge.softplayerjavaapi.SoftplayerJavaApi.models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersService extends BaseService<Users> {

    private final BCryptPasswordEncoder encoder;

    public UsersService(BCryptPasswordEncoder encoder) {
        this.encoder = encoder;
    }

    public Users create(Users model) {
        try {
            model.setPassword(encoder.encode(model.getPassword().trim()));
            return super.create(model);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error", e);
            throw new ApiException("Erro ao salvar o " + model.toString());
        }
    }
}
