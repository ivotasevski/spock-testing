package com.ivo.spocktesting

import com.github.database.rider.core.api.configuration.DBUnit
import com.github.database.rider.core.api.dataset.DataSet
import com.github.database.rider.spring.api.DBRider
import com.ivo.spocktesting.domain.Outbox
import com.ivo.spocktesting.respository.OutboxRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.transaction.support.TransactionTemplate
import spock.lang.Specification

@DBRider()
@DBUnit(alwaysCleanBefore = true, alwaysCleanAfter = true, caseSensitiveTableNames = true)
@Import(TestcontainersConfiguration)
@SpringBootTest
@ContextConfiguration
@ActiveProfiles("test")
class OutboxRepositorySpec extends Specification {

    @Autowired
    OutboxRepository outboxRepository

    @Autowired
    TransactionTemplate transactionTemplate

    @DataSet("datasets/outbox.xml")
    def "should save and load outbox entity"() {
        given: "a new Outbox entity"
        def outbox = new Outbox()
        outbox.setPayload("test ${System.currentTimeMillis()}")

        when: "the entity is persisted inside a transaction"
        transactionTemplate.executeWithoutResult { status ->
            outboxRepository.save(outbox)
        }

        then: "the entity is stored in the repository"
        def outboxes = outboxRepository.findAll()
        !outboxes.isEmpty()
        outboxes.any { it.payload == outbox.payload }
    }

    def "should save and load outbox entity"() {
        given: "Previous test has run"

        when: "the entity is persisted inside a transaction"
        def outboxes = outboxRepository.findAll()
        //
        then: "the entity is stored in the repository"
        outboxes.isEmpty()
    }
}
