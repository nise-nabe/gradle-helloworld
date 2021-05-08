package com.nisecoder.helloworld.gradle

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
