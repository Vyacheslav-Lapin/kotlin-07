package ru.otus.kotlin.kotlin

import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory
import java.util.*

@Configuration
class KafkaTemplateConfiguration(val kafkaProperties: MyKafkaProperties) {


    @Bean
    fun kafkaTemplate()= KafkaTemplate(producerFactory())

    @Bean
    fun producerFactory(): ProducerFactory<Any?, Any?> {
        val config: MutableMap<String, String> = HashMap()
        if (kafkaProperties != null) {
            config[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = kafkaProperties.bootstrapAddress
        }
        config[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java.name
        config[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java.name
        return DefaultKafkaProducerFactory(config as Map<String, Any>)
    }
}
