package com.redis.rediscaching.controllers;

import com.redis.rediscaching.data.document.Producer;
import com.redis.rediscaching.data.repositories.ProducerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/producer")
@RequiredArgsConstructor
public class ProducerController {

    private final ProducerRepository producerRepository;

    @GetMapping
    public ResponseEntity<List<Producer>> getAll() {
        return ResponseEntity.ok(producerRepository.findAll());
    }
}
