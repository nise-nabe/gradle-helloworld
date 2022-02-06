package com.nisecoder.helloworld.gradle

import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.withType

class CodeQualityPlugin: Plugin<Project> {
    override fun apply(project: Project): Unit = project.run {
        plugins.apply("io.gitlab.arturbosch.detekt")

        extensions.configure<DetektExtension> {
            buildUponDefaultConfig = true
            parallel = true
        }

        tasks.withType<Detekt>().configureEach {
            jvmTarget = JavaVersion.VERSION_11.toString()
            reports {
                html.required.set(false)
                xml.required.set(false)
                txt.required.set(false)
                sarif.required.set(true)
            }
        }
    }
}
