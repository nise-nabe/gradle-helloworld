package com.nisecoder.helloworld.gradle

plugins {
    id("net.minecraftforge.gradle")
}


java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(16))
    }
}

minecraft {
    mappings("official", "1.17.1")
}

dependencies {
    minecraft("net.minecraftforge:forge:1.17.1-37.0.27")
}
