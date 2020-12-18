package com.nisecoder

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HelloSpringBootApplication

fun main(args: Array<String>) {
    runApplication<HelloSpringBootApplication>(*args)
}
