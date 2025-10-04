package com.ivo.spocktesting;

import com.ivo.spocktesting.domain.Outbox;
import com.ivo.spocktesting.respository.OutboxRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class SpockTestingApplicationTests {

    @Autowired
    OutboxRepository outboxRepository;

    @Autowired
    TransactionTemplate transactionTemplate;

    @Test
    void contextLoads() {

        Outbox outbox = new Outbox();
        outbox.setPayload("test " + System.currentTimeMillis());
        transactionTemplate.executeWithoutResult(status -> outboxRepository.save(outbox));

        List<Outbox> outboxes = outboxRepository.findAll();
        assert !outboxes.isEmpty();
    }

}
