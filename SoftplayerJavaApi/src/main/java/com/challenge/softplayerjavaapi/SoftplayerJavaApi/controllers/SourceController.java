package com.challenge.softplayerjavaapi.SoftplayerJavaApi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/source")
public class SourceController {

    @GetMapping
    public ModelAndView  getAll() {
        return new ModelAndView("redirect:" + "https://github.com/henriquemrc12/softplayer-java");
    }

}
