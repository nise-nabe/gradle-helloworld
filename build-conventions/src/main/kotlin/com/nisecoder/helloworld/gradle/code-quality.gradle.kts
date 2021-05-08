package com.nisecoder.helloworld.gradle

import io.gitlab.arturbosch.detekt.Detekt

plugins {
    id("io.gitlab.arturbosch.detekt")
}

detekt {
    parallel = true
    reports {
        html.enabled = false
        xml.enabled = false
        txt.enabled = false
        sarif.enabled = true
    }
}

tasks.withType<Detekt>().configureEach {
    jvmTarget = JavaVersion.VERSION_11.toString()
}
