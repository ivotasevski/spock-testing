package com.ivo.spocktesting.service;

import com.ivo.spocktesting.domain.Outbox;
import com.ivo.spocktesting.respository.OutboxRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OutboxService {

    private final OutboxRepository repository;

    public OutboxService(OutboxRepository repository) {
        this.repository = repository;
    }

    public List<Outbox> findAll() {
        return repository.findAll();
    }
}
