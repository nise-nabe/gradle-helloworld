package com.nisecoder.helloworld

import mu.KotlinLogging
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import org.testcontainers.containers.output.Slf4jLogConsumer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import kotlin.test.assertTrue

@Testcontainers
@Tag("containers")
internal class IntegrationTests {
    companion object {
        val logger = KotlinLogging.logger {}
    }

    @Container
    val container = mysql {
        withReuse(true)
        withDatabaseName("db")
        withUsername("user")
        withPassword("password")
        withLogConsumer(Slf4jLogConsumer(logger))
    }

    @Test
    fun test() {
        assertTrue { container.isRunning }
    }
}
