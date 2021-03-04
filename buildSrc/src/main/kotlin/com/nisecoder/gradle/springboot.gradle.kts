package com.nisecoder.gradle

import org.jetbrains.kotlin.gradle.plugin.KotlinPluginWrapper

plugins {
    java
    id("org.springframework.boot")
}

dependencies {
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
        implementation(kotlin("stdlib-jdk8"))
        implementation(kotlin("reflect"))

        testImplementation("com.ninja-squad:springmockk:3.0.1")
    }
}
