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
import org.jetbrains.intellij.IntelliJPluginConstants
import org.jetbrains.intellij.IntelliJPluginExtension
import org.jetbrains.intellij.tasks.PatchPluginXmlTask
import org.jetbrains.intellij.tasks.PrepareSandboxTask
import org.jetbrains.intellij.tasks.RunIdeBase
import org.jetbrains.intellij.tasks.SetupDependenciesTask
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
            type.set("IC")
            version.set("2022.1")
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

        val intellij = extensions.getByType<IntelliJPluginExtension>()

        afterEvaluate {
            // instead of IntellijPluginExtension.configurationDefaultDependencies
            intellij.pluginDependencies.get().forEach { plugin ->
                plugin.jarFiles.forEach {
                    dependencies.add("compileOnly", files(it.toPath()))
                }
            }
        }

        val changelog: ChangelogPluginExtension = extensions.getByType()
        tasks.named<PatchPluginXmlTask>("patchPluginXml") {
            changeNotes.set(provider { changelog.getLatest().toHTML() })
        }

        // avoid destination to be null
        tasks.named<PrepareSandboxTask>("prepareSandbox") {
            destinationDir = project.file("${intellij.sandboxDir.get()}/plugins")
        }
        tasks.named<PrepareSandboxTask>("prepareTestingSandbox") {
            destinationDir = project.file("${intellij.sandboxDir.get()}/plugins-test")
        }
        tasks.named<PrepareSandboxTask>("prepareUiTestingSandbox") {
            destinationDir = project.file("${intellij.sandboxDir.get()}/plugins-uiTest")
        }
        tasks.named<SetupDependenciesTask>(IntelliJPluginConstants.SETUP_DEPENDENCIES_TASK_NAME) {
            idea.get().jarFiles.forEach {
                dependencies.add("compileOnly", files(it.toPath()))
            }
        }

        tasks.withType<RunIdeBase>().configureEach {
            // avoid warning log
            jvmArgs("--add-exports", "java.base/jdk.internal.vm=ALL-UNNAMED")
        }
    }
}
