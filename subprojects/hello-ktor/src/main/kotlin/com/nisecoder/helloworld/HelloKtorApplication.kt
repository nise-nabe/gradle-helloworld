package com.nisecoder.helloworld

import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.routing

fun main() {
    embeddedServer(Netty) {
        configureRouting()
    }
}

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello, world!")
        }
    }
}
