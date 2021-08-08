package com.nisecoder.helloworld.gradle

import io.gitlab.arturbosch.detekt.Detekt
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.api.tasks.SourceSet
import org.gradle.api.tasks.SourceSetContainer
import org.gradle.api.tasks.wrapper.Wrapper
import org.gradle.kotlin.dsl.get
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.register
import org.gradle.kotlin.dsl.withType
import java.util.concurrent.TimeUnit

class RootBuildPlugin: Plugin<Project> {
    override fun apply(project: Project): Unit = project.run {
        plugins.apply("org.jetbrains.gradle.plugin.idea-ext")
        plugins.apply("com.nisecoder.helloworld.gradle.asciidoc")
        plugins.apply("com.nisecoder.helloworld.gradle.code-quality")


        tasks.withType<Wrapper> {
            distributionType = Wrapper.DistributionType.ALL
        }

        allprojects {
            configurations.all {
                resolutionStrategy {
                    cacheChangingModulesFor(0, TimeUnit.SECONDS)
                }
            }
        }

        tasks.register<Detekt>("detektAll") {
            description = "Runs a all detekt build."

            buildUponDefaultConfig = true
            parallel = true
            reports {
                html.enabled = false
                xml.enabled = false
                txt.enabled = false
                sarif.enabled = true
            }

            basePath = projectDir.path

            jvmTarget = JavaVersion.VERSION_11.toString()

            setSource(subprojects.filter {
                it.pluginManager.hasPlugin("com.nisecoder.helloworld.gradle.kotlin")
            }.map {
                (it as ExtensionAware).extensions.getByType<SourceSetContainer>()
            }.map {
                it[SourceSet.MAIN_SOURCE_SET_NAME].allJava.sourceDirectories
            })
        }
    }
}
