package com.nisecoder.helloworld

import org.komamitsu.fluency.fluentd.FluencyBuilderForFluentd

fun main() {
    FluencyBuilderForFluentd().build().use { logger ->
        logger.emit("tag", mapOf("aaa" to "bbb"))
    }
}
