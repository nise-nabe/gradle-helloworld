package com.nisecoder.helloworld

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.embedded.jetty.JettyWebServer
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext
import kotlin.test.assertEquals


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class HelloSpringBootWebMvcJettyApplicationTest(
    @Autowired val context: ServletWebServerApplicationContext
) {
    @Test
    fun testHome() {
        assertEquals(JettyWebServer::class, context.webServer::class)
    }
}
