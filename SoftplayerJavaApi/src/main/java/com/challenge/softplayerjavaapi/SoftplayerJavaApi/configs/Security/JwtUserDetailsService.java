package com.challenge.softplayerjavaapi.SoftplayerJavaApi.configs.Security;


import com.challenge.softplayerjavaapi.SoftplayerJavaApi.exception.ApiException;
import com.challenge.softplayerjavaapi.SoftplayerJavaApi.models.Users;
import com.challenge.softplayerjavaapi.SoftplayerJavaApi.repositories.UsersRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final UsersRepository usersRepository;

    public JwtUserDetailsService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Users user = usersRepository.findByEmail(email)
                .orElseThrow(()-> new ApiException("Usuário não encontrado no sistema!"));

        return new User(user.getEmail(), user.getPassword(),
                       new ArrayList<>());
    }
}