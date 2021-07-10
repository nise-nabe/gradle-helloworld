package com.nisecoder.helloworld

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class HelloLibTest {
    @Test
    fun helloTest() {
        val helloLib = HelloLib()

        assertEquals("Hello test", helloLib.hello("test"))
    }
}
