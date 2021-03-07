package com.nisecoder.helloworld.gradle

import org.springframework.boot.gradle.plugin.SpringBootPlugin

plugins {
    java
    id("org.springframework.boot")
    id("com.nisecoder.gradle.springboot-base")
}

dependencies {
    developmentOnly(platform(SpringBootPlugin.BOM_COORDINATES))
}
