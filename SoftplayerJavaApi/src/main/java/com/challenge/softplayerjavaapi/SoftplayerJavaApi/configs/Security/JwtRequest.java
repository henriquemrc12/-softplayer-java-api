package com.challenge.softplayerjavaapi.SoftplayerJavaApi.configs.Security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JwtRequest {

    public String email;

    public String password;
}
