package com.nisecoder.helloworld.gradle

import org.springframework.boot.gradle.plugin.SpringBootPlugin

plugins {
    `java-library`
    id("com.nisecoder.helloworld.gradle.springboot-base")
}

sourceSets {
    create("springboot") {
        val mainSourceSet = sourceSets[SourceSet.MAIN_SOURCE_SET_NAME]
        val mainOutput = objects.fileCollection().from(mainSourceSet.output)

        compileClasspath += mainOutput
        runtimeClasspath += mainOutput

        configurations.getByName(implementationConfigurationName)
                .extendsFrom(configurations.getByName(mainSourceSet.implementationConfigurationName))
        configurations.getByName(apiConfigurationName)
                .extendsFrom(configurations.getByName(mainSourceSet.apiConfigurationName))
    }
}

java {
    registerFeature("springboot") {
        usingSourceSet(sourceSets["springboot"])
    }
}

dependencies {
    api(platform(SpringBootPlugin.BOM_COORDINATES))

    val springbootApi by configurations.getting {}
    springbootApi(platform(SpringBootPlugin.BOM_COORDINATES))
    springbootApi("org.springframework.boot:spring-boot-starter")
}
