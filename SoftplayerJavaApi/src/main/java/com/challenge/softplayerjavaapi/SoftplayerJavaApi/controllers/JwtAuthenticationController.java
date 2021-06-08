package com.challenge.softplayerjavaapi.SoftplayerJavaApi.controllers;


import com.challenge.softplayerjavaapi.SoftplayerJavaApi.configs.Security.JwtRequest;
import com.challenge.softplayerjavaapi.SoftplayerJavaApi.configs.Security.JwtResponse;
import com.challenge.softplayerjavaapi.SoftplayerJavaApi.configs.Security.JwtTokenUtil;
import com.challenge.softplayerjavaapi.SoftplayerJavaApi.configs.Security.JwtUserDetailsService;
import com.challenge.softplayerjavaapi.SoftplayerJavaApi.exception.ApiException;
import com.challenge.softplayerjavaapi.SoftplayerJavaApi.models.Users;
import com.challenge.softplayerjavaapi.SoftplayerJavaApi.repositories.UsersRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenUtil jwtTokenUtil;

    private final JwtUserDetailsService userDetailsService;

    private final UsersRepository usersRepository;

    public JwtAuthenticationController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, JwtUserDetailsService userDetailsService, UsersRepository usersRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
        this.usersRepository = usersRepository;
    }

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody @Valid JwtRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getEmail().trim(), authenticationRequest.getPassword().trim());
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getEmail().trim());
        final String token = jwtTokenUtil.generateToken(userDetails);

        Users user = usersRepository.findByEmail(jwtTokenUtil.getUsernameFromToken(token))
                .orElseThrow(() -> new ApiException("Usuário não encontrado no sistema"));

        return ResponseEntity.ok().body(new JwtResponse(token, user.getId()));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            e.printStackTrace();
            throw new ApiException("E-mail ou senha incorretos!");
        }
    }
}