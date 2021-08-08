package com.nisecoder.helloworld.gradle

import org.asciidoctor.gradle.jvm.AsciidoctorTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.named

class AsciidocPlugin: Plugin<Project> {
    override fun apply(project: Project): Unit = project.run {
        plugins.apply("org.asciidoctor.jvm.convert")

        tasks.named<AsciidoctorTask>("asciidoctor") {
            // collect into root buildDirectory for publishing to Github Pages
            val outputDir = if (project == rootProject) {
                rootProject.buildDir.resolve("docs/asciidoc")
            } else {
                rootProject.buildDir.resolve("docs/asciidoc/${project.name}")
            }
            setOutputDir(outputDir)
        }

    }
}
