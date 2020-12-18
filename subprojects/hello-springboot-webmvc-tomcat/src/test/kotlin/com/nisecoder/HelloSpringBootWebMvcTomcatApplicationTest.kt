package com.nisecoder

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.embedded.tomcat.TomcatWebServer
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext
import kotlin.test.assertEquals


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class HelloSpringBootWebMvcTomcatApplicationTest(
    @Autowired val context: ServletWebServerApplicationContext
) {
    @Test
    fun testWebServer() {
        assertEquals(TomcatWebServer::class, context.webServer::class)
    }
}
