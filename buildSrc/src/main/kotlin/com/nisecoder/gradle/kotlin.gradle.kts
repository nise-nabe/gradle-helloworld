package com.nisecoder.gradle

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
}

val compiler: Provider<JavaCompiler> = javaToolchains.compilerFor {
    languageVersion.set(JavaLanguageVersion.of(15))
    vendor.set(JvmVendorSpec.ADOPTOPENJDK)
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_15.toString()
    }
}

dependencies {
    testImplementation(kotlin("test-junit5"))
}
