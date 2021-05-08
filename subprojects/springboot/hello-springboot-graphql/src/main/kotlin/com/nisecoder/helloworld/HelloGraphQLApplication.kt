package com.nisecoder.helloworld

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HelloGraphQLApplication

fun main(args: Array<String>) {
    runApplication<HelloGraphQLApplication>(args = args)
}
