package com.nisecoder.helloworld.gradle

import io.gitlab.arturbosch.detekt.Detekt
import java.util.concurrent.TimeUnit

plugins {
    id("org.jetbrains.gradle.plugin.idea-ext")
    id("com.nisecoder.helloworld.gradle.asciidoc")
    id("com.nisecoder.helloworld.gradle.code-quality")
    id("com.nisecoder.helloworld.gradle.shared-index")
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
        html.required.set(false)
        xml.required.set(false)
        txt.required.set(false)
        sarif.required.set(true)
    }

    basePath = projectDir.path

    jvmTarget = JavaVersion.VERSION_19.toString()

    setSource(subprojects.filter {
        it.pluginManager.hasPlugin("com.nisecoder.helloworld.gradle.kotlin")
    }.map {
        (it as ExtensionAware).extensions.getByType<SourceSetContainer>()
    }.map {
        it[SourceSet.MAIN_SOURCE_SET_NAME].allJava.sourceDirectories
    })
}
