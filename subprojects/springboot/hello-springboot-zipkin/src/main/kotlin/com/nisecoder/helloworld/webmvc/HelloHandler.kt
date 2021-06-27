package com.nisecoder.helloworld.webmvc

import mu.KotlinLogging
import org.springframework.stereotype.Component
import org.springframework.web.servlet.function.ServerRequest
import org.springframework.web.servlet.function.ServerResponse

@Component
class HelloHandler {
    private val logger = KotlinLogging.logger {}

    fun handle(request: ServerRequest): ServerResponse {
        logger.info { "Hello, World." }
        return ServerResponse.ok().build()
    }
}