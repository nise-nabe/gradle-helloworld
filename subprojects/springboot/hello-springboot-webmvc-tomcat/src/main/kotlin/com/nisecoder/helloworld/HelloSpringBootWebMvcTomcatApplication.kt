package com.nisecoder.helloworld

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HelloSpringBootWebMvcTomcatApplication

fun main(args: Array<String>) {
    runApplication<HelloSpringBootWebMvcTomcatApplication>(args = args)
}
