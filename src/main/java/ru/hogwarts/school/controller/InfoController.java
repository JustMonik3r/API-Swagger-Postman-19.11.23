package ru.hogwarts.school.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@RestController
@RequestMapping("/info")
public class InfoController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/port")
    public String getPort() {
        return port;
    }

    @GetMapping("/port/{maxSize}")
    public int getInteger(@PathVariable Integer maxSize) {
        maxSize = 1_000_000;
        int sum = Stream.iterate(1, a -> a + 1)
                .limit(maxSize)
                .reduce(0, (a, b) -> a + b);
        Long time = System.currentTimeMillis();
        return sum;
    }
}
