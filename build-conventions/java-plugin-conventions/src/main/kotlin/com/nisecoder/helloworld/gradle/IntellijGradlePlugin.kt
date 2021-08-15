package com.nisecoder.helloworld.gradle

import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.named
import org.gradle.kotlin.dsl.withType
import org.jetbrains.changelog.ChangelogPlugin
import org.jetbrains.changelog.ChangelogPluginExtension
import org.jetbrains.intellij.IntelliJPlugin
import org.jetbrains.intellij.IntelliJPluginExtension
import org.jetbrains.intellij.tasks.PatchPluginXmlTask
import org.jetbrains.intellij.tasks.RunIdeTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class IntellijGradlePlugin: Plugin<Project> {
    override fun apply(project: Project): Unit = project.run {
        plugins.apply(IntelliJPlugin::class)
        plugins.apply(ChangelogPlugin::class)

        tasks.withType<KotlinCompile>().configureEach {
            kotlinOptions {
                languageVersion = "1.5"
                apiVersion = "1.5"
                jvmTarget = JavaVersion.VERSION_11.toString()
                javaParameters = true
            }
        }

        extensions.configure<IntelliJPluginExtension>("intellij") {
            version.set("IC-2021.2")
            configureDefaultDependencies.set(false)

            type.set("IC")
            downloadSources.set(true)
            updateSinceUntilBuild.set(true)
            sameSinceUntilBuild.set(true)
        }

        val changelog: ChangelogPluginExtension = extensions.getByType()
        tasks.named<PatchPluginXmlTask>("patchPluginXml") {
            changeNotes.set(provider { changelog.getLatest().toHTML() })
        }

        tasks.named<RunIdeTask>("runIde") {
            // avoid warning log
            jvmArgs("--add-exports", "java.base/jdk.internal.vm=ALL-UNNAMED")
        }
    }
}
