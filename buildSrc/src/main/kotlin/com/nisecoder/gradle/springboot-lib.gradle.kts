package com.nisecoder.gradle

import org.jetbrains.kotlin.gradle.plugin.KotlinPluginWrapper
import org.springframework.boot.gradle.plugin.SpringBootPlugin

plugins {
    `java-library`
    id("com.nisecoder.gradle.springboot-base")
}

dependencies {
    api(platform(SpringBootPlugin.BOM_COORDINATES))
}
