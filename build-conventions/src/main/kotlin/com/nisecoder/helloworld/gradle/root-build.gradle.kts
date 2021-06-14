package com.nisecoder.helloworld.gradle

import io.gitlab.arturbosch.detekt.Detekt
import org.gradle.api.tasks.wrapper.Wrapper
import java.util.concurrent.TimeUnit

plugins {
    id("org.jetbrains.gradle.plugin.idea-ext")
    id("com.nisecoder.helloworld.gradle.asciidoc")
    id("com.nisecoder.helloworld.gradle.code-quality")
}

tasks.withType<Wrapper> {
    distributionType = Wrapper.DistributionType.ALL
}

allprojects {
    configurations.all {
        resolutionStrategy {
            cacheChangingModulesFor(0, TimeUnit.SECONDS)
        }
    }
}


tasks.register<Detekt>("detektAll") {
    description = "Runs a all detekt build."

    buildUponDefaultConfig = true
    parallel = true
    reports {
        html.enabled = false
        xml.enabled = false
        txt.enabled = false
        sarif.enabled = true
    }

    basePath = projectDir.path

    jvmTarget = JavaVersion.VERSION_11.toString()

    setSource(subprojects.filter {
        it.pluginManager.hasPlugin("com.nisecoder.helloworld.gradle.kotlin")
    }.map {
        it.sourceSets[SourceSet.MAIN_SOURCE_SET_NAME].allJava.sourceDirectories
    })
}
