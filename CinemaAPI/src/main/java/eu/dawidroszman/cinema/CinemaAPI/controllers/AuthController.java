package eu.dawidroszman.cinema.CinemaAPI.controllers;

import eu.dawidroszman.cinema.CinemaAPI.requests.LoginRequest;
import eu.dawidroszman.cinema.CinemaAPI.requests.RegisterRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("auth")
public class AuthController {


//    private final AuthenticationManager authenticationManager;


    @GetMapping("/login")
    public HashMap login() {
        OAuth2User user = ((OAuth2User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return new HashMap(){{
            put("hello", user.getAttribute("name"));
            put("Email", user.getAttribute("email"));
        }};
    }

//    public AuthController(TokenService tokenService, AuthenticationManager authenticationManager, AuthService authService) {
//        this.tokenService = tokenService;
//        this.authenticationManager = authenticationManager;
//        this.authService = authService;
//    }
//
//    @PostMapping("/login")
//    public String token(@RequestBody LoginRequest userLogin) throws AuthenticationException {
//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLogin.getUsername(), userLogin.getPassword()));
//        return tokenService.generateToken(authentication);
//    }
//
//    @PostMapping("/register")
//    public Boolean register(@RequestBody RegisterRequest request) {
//        return authService.register(request);
//    }

}
