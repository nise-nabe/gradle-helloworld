package com.nisecoder.helloworld.gradle

plugins {
    id("org.asciidoctor.jvm.convert")
}


tasks.asciidoctor {
    setOutputDir(rootProject.buildDir.resolve("docs/asciidoc/${project.name}"))
}
