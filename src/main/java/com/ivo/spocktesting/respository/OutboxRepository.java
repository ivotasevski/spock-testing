package com.ivo.spocktesting.respository;

import com.ivo.spocktesting.domain.Outbox;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OutboxRepository extends JpaRepository<Outbox, Long> {
}
