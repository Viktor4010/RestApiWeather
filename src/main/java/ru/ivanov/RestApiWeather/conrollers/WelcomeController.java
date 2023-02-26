package ru.ivanov.RestApiWeather.conrollers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "welcome", produces = MediaType.TEXT_HTML_VALUE)
public class WelcomeController {
    @GetMapping
    public String welcome() {
        return "Welcome to Weather Rest Api, dear user!!! Use it properly. Relax and enjoy!";
    }

}
