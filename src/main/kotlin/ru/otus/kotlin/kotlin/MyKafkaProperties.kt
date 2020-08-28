package ru.otus.kotlin.kotlin

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

//@ConfigurationProperties("mykafka")
@Component
class MyKafkaProperties(@Value("localhost:9092") val bootstrapAddress: String)
