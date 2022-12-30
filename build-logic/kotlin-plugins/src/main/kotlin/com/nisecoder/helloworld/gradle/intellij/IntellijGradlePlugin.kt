package com.nisecoder.helloworld.gradle.intellij

import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.named
import org.gradle.kotlin.dsl.provideDelegate
import org.gradle.kotlin.dsl.withType
import org.jetbrains.changelog.ChangelogPlugin
import org.jetbrains.changelog.ChangelogPluginExtension
import org.jetbrains.intellij.IntelliJPlugin
import org.jetbrains.intellij.IntelliJPluginExtension
import org.jetbrains.intellij.tasks.PatchPluginXmlTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class IntellijGradlePlugin: Plugin<Project> {
    override fun apply(project: Project): Unit = project.run {
        plugins.apply(IntelliJPlugin::class)
        plugins.apply(ChangelogPlugin::class)

        tasks.withType<KotlinCompile>().configureEach {
            kotlinOptions {
                languageVersion = "1.8"
                apiVersion = "1.8"
                jvmTarget = JavaVersion.VERSION_17.toString()
                javaParameters = true
            }
        }

        extensions.configure<IntelliJPluginExtension>("intellij") {
            type.set("IC")
            version.set("2022.3.1")
            configureDefaultDependencies.set(false)
            downloadSources.set(true)

            if (project.hasProperty("intellijLocalPath")) {
                val intellijLocalPath: String by project
                localPath.set(intellijLocalPath)
                downloadSources.set(false)
            }

            updateSinceUntilBuild.set(true)
            sameSinceUntilBuild.set(true)
        }

        val changelog: ChangelogPluginExtension = extensions.getByType()
        tasks.named<PatchPluginXmlTask>("patchPluginXml") {
            changeNotes.set(provider { changelog.getLatest().toHTML() })
        }
    }
}
