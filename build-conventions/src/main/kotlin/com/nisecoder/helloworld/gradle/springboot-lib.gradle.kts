package com.nisecoder.helloworld.gradle

import org.springframework.boot.gradle.plugin.SpringBootPlugin

plugins {
    `java-library`
    id("com.nisecoder.helloworld.gradle.springboot-base")
}

dependencies {
    api(platform(SpringBootPlugin.BOM_COORDINATES))
}
