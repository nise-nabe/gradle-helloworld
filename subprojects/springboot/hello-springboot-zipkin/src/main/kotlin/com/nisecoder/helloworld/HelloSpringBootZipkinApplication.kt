package com.nisecoder.helloworld

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HelloSpringBootZipkinApplication

fun main(args: Array<String>) {
    runApplication<HelloSpringBootZipkinApplication>(args = args)
}
