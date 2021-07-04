package com.nisecoder.helloworld.gradle

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    id("com.nisecoder.helloworld.gradle.code-quality")
}

val compiler: Provider<JavaCompiler> = javaToolchains.compilerFor {
    @Suppress("MagicNumber")
    languageVersion.set(JavaLanguageVersion.of(15))
    vendor.set(JvmVendorSpec.ADOPTOPENJDK)
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        languageVersion = "1.5"
        apiVersion = "1.5"
        jvmTarget = JavaVersion.VERSION_11.toString()
        javaParameters = true
        useIR = true

        jdkHome = compiler.get().metadata.installationPath.asFile.absolutePath
    }
}

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom:1.5.20"))

    testImplementation(kotlin("test-junit5"))
}

tasks.test {
    maxParallelForks = (Runtime.getRuntime().availableProcessors() / 2).takeIf { it > 0 } ?: 1

    reports {
        junitXml.apply {
            required.set(true)
            outputLocation.set(rootProject.layout.buildDirectory.dir("test-results/junit/${project.name}"))
        }
        html.apply {
            required.set(true)
            outputLocation.set(rootProject.layout.buildDirectory.dir("reports/tests/test/${project.name}"))
        }
    }
}
