package com.nisecoder.helloworld.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPlugin
import org.gradle.api.plugins.JavaPlugin.IMPLEMENTATION_CONFIGURATION_NAME
import org.gradle.api.plugins.JavaPlugin.TEST_IMPLEMENTATION_CONFIGURATION_NAME
import org.gradle.kotlin.dsl.kotlin
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.plugin.KotlinPluginWrapper
import org.springframework.boot.gradle.plugin.SpringBootPlugin

class SpringBootConventionPlugin: Plugin<Project> {
    override fun apply(project: Project): Unit = project.run {
        plugins.apply(JavaPlugin::class.java)

        dependencies.apply {
            add(IMPLEMENTATION_CONFIGURATION_NAME, platform(SpringBootPlugin.BOM_COORDINATES))
            add(IMPLEMENTATION_CONFIGURATION_NAME, "org.springframework.boot:spring-boot-starter")
            add(TEST_IMPLEMENTATION_CONFIGURATION_NAME, "org.springframework.boot:spring-boot-starter-test")
        }

        plugins.withType<KotlinPluginWrapper> {
            plugins.apply("org.jetbrains.kotlin.plugin.spring")
            plugins.apply("org.jetbrains.kotlin.plugin.noarg")
            plugins.apply("org.jetbrains.kotlin.kapt")

            dependencies.apply {
                add("kapt", platform(SpringBootPlugin.BOM_COORDINATES))

                add(IMPLEMENTATION_CONFIGURATION_NAME, kotlin("stdlib-jdk8"))
                add(IMPLEMENTATION_CONFIGURATION_NAME, kotlin("reflect"))
                add(TEST_IMPLEMENTATION_CONFIGURATION_NAME, "com.ninja-squad:springmockk:4.0.0")
            }

        }
    }
}
