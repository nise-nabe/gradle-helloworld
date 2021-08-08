package com.nisecoder.helloworld.gradle

import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.jvm.toolchain.JavaLanguageVersion
import org.gradle.jvm.toolchain.JavaToolchainService
import org.gradle.jvm.toolchain.JvmVendorSpec
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.getByName
import org.gradle.kotlin.dsl.withType
import org.jetbrains.intellij.IntelliJPlugin
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class IntellijGradlePlugin: Plugin<Project> {
    override fun apply(project: Project): Unit = project.run {
        plugins.apply(IntelliJPlugin::class)
        val compiler = extensions.getByName<JavaToolchainService>("javaToolchains") .compilerFor {
            @Suppress("MagicNumber")
            languageVersion.set(JavaLanguageVersion.of(11))
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
    }
}
