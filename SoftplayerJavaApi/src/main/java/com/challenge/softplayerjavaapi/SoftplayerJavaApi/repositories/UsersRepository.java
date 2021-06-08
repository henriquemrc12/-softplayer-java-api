package com.challenge.softplayerjavaapi.SoftplayerJavaApi.repositories;

import com.challenge.softplayerjavaapi.SoftplayerJavaApi.models.Users;

import java.util.Optional;

public interface UsersRepository extends BaseRepository<Users> {
    Optional<Users> findByEmail(String email);
}
