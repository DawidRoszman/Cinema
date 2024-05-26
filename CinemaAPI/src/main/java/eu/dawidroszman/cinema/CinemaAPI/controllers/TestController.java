package eu.dawidroszman.cinema.CinemaAPI.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("")
public class TestController {

    @GetMapping("")
    public String test(Principal principal) {
        return "Hello World!"+principal.getName();
    }
}
