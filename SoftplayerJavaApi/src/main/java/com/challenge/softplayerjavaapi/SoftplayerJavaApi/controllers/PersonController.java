package com.challenge.softplayerjavaapi.SoftplayerJavaApi.controllers;

import com.challenge.softplayerjavaapi.SoftplayerJavaApi.models.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/person")
public class PersonController extends BaseController<Person> {
}
