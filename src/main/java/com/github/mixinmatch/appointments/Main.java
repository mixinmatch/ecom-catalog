package com.github.mixinmatch.appointments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.Collections;

@SpringBootApplication
public class Main {

    static void main() {
        SpringApplication.run(Main.class);
    }
}
