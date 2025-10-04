package com.ivo.spocktesting.controller;

import com.ivo.spocktesting.domain.Outbox;
import com.ivo.spocktesting.service.OutboxService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/outbox")
public class OutboxController {

    private final OutboxService service;

    public OutboxController(OutboxService service) {
        this.service = service;
    }

    @GetMapping
    public List<Outbox> getAll() {
        return service.findAll();
    }
}
