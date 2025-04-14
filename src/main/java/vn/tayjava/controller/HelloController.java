package vn.tayjava.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class HelloController {

    @GetMapping
    public String hello() {
        return "<h1>Welcome to Appointment Service...!!! </h1>" +

                "<h2>Swagger API:</h2><br/>" +
                "<a href=\"https://appointment-service-e6za.onrender.com/swagger-ui/index.html#/\">" +
                "https://appointment-service-e6za.onrender.com/swagger-ui/index.html#/</a>";
    }
}
