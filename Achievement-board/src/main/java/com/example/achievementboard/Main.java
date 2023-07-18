package com.example.achievementboard;

import com.example.achievementboard.service.seed.SeedService;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Main implements CommandLineRunner {
    private SeedService service;
    @Override
    public void run(String... args) throws Exception {
        service.seedAll();

    }
}
