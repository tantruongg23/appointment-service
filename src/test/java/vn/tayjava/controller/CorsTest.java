package vn.tayjava.controller; // "tayjava" is a valid package name, no changes needed here.

import org.junit.jupiter.api.Test;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.options;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(AppointmentController.class)
@AutoConfigureMockMvc
public class CorsTest {

    @Autowired
    MockMvc mvc;

    @Test
    void preflight_should_return_cors_headers() throws Exception {
        mvc.perform(options("/api/v1/appointments")
                .header("Origin", "http://localhost:3000") // mandatory
                .header("Access-Control-Request-Method", "GET")) // mandatory
                .andExpect(status().isOk())
                .andExpect(header().string("Access-Control-Allow-Origin",
                        "http://localhost:3000"))
                .andExpect(header().string("Access-Control-Allow-Methods",
                        Matchers.containsString("GET")));
    }
}