package com.challenge.softplayerjavaapi.SoftplayerJavaApi.controllers;

import com.challenge.softplayerjavaapi.SoftplayerJavaApi.models.Users;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/users")
public class UsersController extends BaseController<Users> {
}
