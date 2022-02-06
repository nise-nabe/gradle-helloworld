package com.nisecoder.helloworld.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.ApplicationPlugin
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.named
import org.springframework.boot.gradle.plugin.SpringBootPlugin

class SpringBootApplicationPlugin: Plugin<Project> {
    override fun apply(project: Project): Unit = project.run {
        plugins.apply(ApplicationPlugin::class.java)
        plugins.apply(SpringBootPlugin::class.java)
        plugins.apply(SpringBootConventionPlugin::class.java)

        dependencies.apply {
            add("developmentOnly", platform(SpringBootPlugin.BOM_COORDINATES))
        }

        tasks.named<Test>("test") {
            useJUnitPlatform()
        }
    }
}
