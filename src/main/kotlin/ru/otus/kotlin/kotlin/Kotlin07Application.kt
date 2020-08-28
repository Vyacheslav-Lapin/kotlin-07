package ru.otus.kotlin.kotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
//@EnableConfigurationProperties(MyKafkaProperties::class)
class Kotlin07Application

fun main(args: Array<String>) {
	runApplication<Kotlin07Application>(*args)
}
