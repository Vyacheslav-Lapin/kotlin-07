package ru.otus.kotlin.kotlin

import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import org.springframework.util.concurrent.ListenableFutureCallback
import java.time.LocalDateTime

@Service
class KafkaProducerService(val kafkaTemplate: KafkaTemplate<Any?, Any?>) {

    private val log = LoggerFactory.getLogger(KafkaProducerService::class.java)

    @Scheduled(fixedRate = 1_000 * 10, initialDelay = 5 * 1_000)
    fun produceMessage() {
        log.info("Produce Message - BEGIN")
        val message = "hello ${runningId++} this is a kafka message ${LocalDateTime.now()}"
        val listenableFuture = kafkaTemplate.send("mike", message)
        listenableFuture.addCallback(object : ListenableFutureCallback<Any?> {
            override fun onFailure(ex: Throwable) = log.error("ERROR Kafka error happened", ex)
            override fun onSuccess(result: Any?) = log.info("SUCCESS!!! This is the result: {}", result)
        })
        log.info("Produce Message - END {}", message)
    }

    companion object {
        private var runningId = 0
    }
}
