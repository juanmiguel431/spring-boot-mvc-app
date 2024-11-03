package com.example.demo.services;

import org.springframework.stereotype.Service;
import java.util.Objects;

@Service
public class FortuneService {

    public String getFortune(String name) throws Throwable {

        Thread.sleep(3000);

        if (Objects.equals(name, "Juan Miguel")) {
            return "You are rich.";
        }

        return "You are poor.";
    }
}
