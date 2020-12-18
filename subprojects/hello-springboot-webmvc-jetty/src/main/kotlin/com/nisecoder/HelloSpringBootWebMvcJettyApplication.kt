package com.nisecoder

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HelloSpringBootWebMvcJettyApplication

fun main(args: Array<String>) {
    runApplication<HelloSpringBootWebMvcJettyApplication>(*args)
}
