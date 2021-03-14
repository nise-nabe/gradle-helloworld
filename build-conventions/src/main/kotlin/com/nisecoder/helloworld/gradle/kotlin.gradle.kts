package com.nisecoder.helloworld.gradle

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
        languageVersion = "1.4"
        jvmTarget = JavaVersion.VERSION_15.toString()
        javaParameters = true
        useIR = true
    }
}

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom:1.4.31"))

    testImplementation(kotlin("test-junit5"))
}

tasks.test {
    maxParallelForks = (Runtime.getRuntime().availableProcessors() / 2).takeIf { it > 0 } ?: 1

    reports {
        junitXml.apply {
            isEnabled = true
            outputLocation.set(rootProject.layout.buildDirectory.dir("test-results/junit/${project.name}"))
        }
        html.apply {
            isEnabled = true
            outputLocation.set(rootProject.layout.buildDirectory.dir("reports/tests/test/${project.name}"))
        }
    }
}
