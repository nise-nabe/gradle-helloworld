package com.nisecoder.helloworld.gradle

import org.gradle.api.tasks.wrapper.Wrapper
import java.util.concurrent.TimeUnit

plugins {
    id("org.jetbrains.gradle.plugin.idea-ext")
    id("com.nisecoder.helloworld.gradle.asciidoc")
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
