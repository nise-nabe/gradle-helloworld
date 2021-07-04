package com.nisecoder.helloworld

import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import kotlin.test.assertTrue

@Testcontainers
@Tag("container")
class IntegrationTests {
    @Container
    val container = mysql {
        withDatabaseName("db")
        withUsername("user")
        withPassword("password")
    }

    @Test
    fun test() {
        assertTrue { container.isRunning }
    }
}
