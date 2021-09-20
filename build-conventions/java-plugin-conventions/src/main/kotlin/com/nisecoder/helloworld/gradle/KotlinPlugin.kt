package com.nisecoder.helloworld.gradle

import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test
import org.gradle.jvm.toolchain.JavaLanguageVersion
import org.gradle.jvm.toolchain.JavaToolchainService
import org.gradle.jvm.toolchain.JvmVendorSpec
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.getByName
import org.gradle.kotlin.dsl.kotlin
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class KotlinPlugin: Plugin<Project> {
    override fun apply(project: Project) : Unit = project.run {
        plugins.apply("org.jetbrains.kotlin.jvm")
        plugins.apply(CodeQualityPlugin::class)

        val compiler = extensions.getByName<JavaToolchainService>("javaToolchains") .compilerFor {
                @Suppress("MagicNumber")
                languageVersion.set(JavaLanguageVersion.of(15))
                vendor.set(JvmVendorSpec.ADOPTOPENJDK)
        }

        tasks.withType<KotlinCompile>().configureEach {
            kotlinOptions {
                languageVersion = "1.5"
                apiVersion = "1.5"
                jvmTarget = JavaVersion.VERSION_11.toString()
                javaParameters = true

                jdkHome = compiler.get().metadata.installationPath.asFile.absolutePath
            }
        }

        dependencies.apply {
            add("implementation", platform("org.jetbrains.kotlin:kotlin-bom:1.5.30"))
            add("testImplementation", platform("org.jetbrains.kotlin:kotlin-bom:1.5.30"))
            add("testImplementation", kotlin("test"))
        }

        tasks.getByName<Test>("test") {
            maxParallelForks = (Runtime.getRuntime().availableProcessors() / 2).takeIf { it > 0 } ?: 1

            reports {
                junitXml.apply {
                    required.set(true)
                    outputLocation.set(rootProject.layout.buildDirectory.dir("test-results/junit/${project.name}"))
                }
                html.apply {
                    required.set(true)
                    outputLocation.set(rootProject.layout.buildDirectory.dir("reports/tests/test/${project.name}"))
                }
            }
        }
    }
}