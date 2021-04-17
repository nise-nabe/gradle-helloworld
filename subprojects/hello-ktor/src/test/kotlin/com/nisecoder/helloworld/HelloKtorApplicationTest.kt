package com.nisecoder.helloworld

import io.ktor.application.Application
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.handleRequest
import io.ktor.server.testing.withTestApplication
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class HelloKtorApplicationTest {
    @Test
    fun testRequest() = withTestApplication(Application::module) {
        with(handleRequest(HttpMethod.Get, "/")) {
            assertEquals(HttpStatusCode.OK, response.status())
            assertEquals("Hello, world!", response.content)
        }
    }
}
