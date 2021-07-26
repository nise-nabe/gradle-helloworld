package com.nisecoder.helloworld.gradle

plugins {
    id("org.asciidoctor.jvm.convert")
}

tasks.asciidoctor {
    // collect into root buildDirectory for publishing to Github Pages
    val outputDir = if (project == rootProject) {
        rootProject.buildDir.resolve("docs/asciidoc")
    } else {
        rootProject.buildDir.resolve("docs/asciidoc/${project.name}")
    }
    setOutputDir(outputDir)
}
