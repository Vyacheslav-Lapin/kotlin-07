package ru.otus.kotlin.kotlin

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.stereotype.Service
import java.util.*

@EnableKafka
@Configuration
class ConsumerConfiguration(private val kafkaProperties: MyKafkaProperties) {

    @Bean
    fun containerFactory(): ConcurrentKafkaListenerContainerFactory<*, *> {
        val kafkaListenerContainerFactory: ConcurrentKafkaListenerContainerFactory<Any?, Any?> =
                ConcurrentKafkaListenerContainerFactory()
        kafkaListenerContainerFactory.consumerFactory = consumerFactory()
        return kafkaListenerContainerFactory
    }

    @Bean
    fun consumerFactory(): ConsumerFactory<Any?, Any?> {
        val config: MutableMap<String, Any> = HashMap()
        config[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = kafkaProperties.bootstrapAddress
        config[ConsumerConfig.GROUP_ID_CONFIG] = CONSUMER_GROUP_ID
        config[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        config[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        return DefaultKafkaConsumerFactory(config)
    }
}

const val CONSUMER_GROUP_ID = "mikesconsumergroup"

@Service
class ConsumerService {

    @KafkaListener(topics = ["mike"], groupId = CONSUMER_GROUP_ID)
    fun consumeMessages(message: String) {
        log.info("CONSUMER: We received a message!!! {}", message)
    }

    companion object {
        private val log = LoggerFactory.getLogger(ConsumerService::class.java)
    }
}
