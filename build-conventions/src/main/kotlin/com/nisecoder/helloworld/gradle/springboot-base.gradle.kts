package com.nisecoder.helloworld.gradle

import org.jetbrains.kotlin.gradle.plugin.KotlinPluginWrapper
import org.springframework.boot.gradle.plugin.SpringBootPlugin

plugins {
    java
}

dependencies {
    implementation(platform(SpringBootPlugin.BOM_COORDINATES))

    implementation("org.springframework.boot:spring-boot-starter")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

plugins.withType<KotlinPluginWrapper> {
    plugins {
        kotlin("plugin.spring")
        kotlin("plugin.noarg")
        kotlin("kapt")
    }

    dependencies {
        val kapt by configurations.getting {}
        kapt(platform(SpringBootPlugin.BOM_COORDINATES))

        implementation(kotlin("stdlib-jdk8"))
        implementation(kotlin("reflect"))

        testImplementation("com.ninja-squad:springmockk:3.0.1")
    }
}
