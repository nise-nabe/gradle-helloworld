package com.nisecoder.helloworld.gradle

plugins {
    id("org.jetbrains.gradle.plugin.idea-ext")
}

idea {
    module {
        isDownloadJavadoc = true
        isDownloadSources = true
    }
}
