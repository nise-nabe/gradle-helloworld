package com.nisecoder.helloworld.gradle.intellij

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.artifacts.Configuration
import org.gradle.api.plugins.JavaPlugin
import org.gradle.api.tasks.JavaExec
import org.gradle.api.tasks.TaskProvider
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.register
import java.io.File

class IntellijSharedIndexPlugin: Plugin<Project> {
    override fun apply(project: Project): Unit = project.run {
        plugins.apply(JavaPlugin::class)
        plugins.apply(IntellijRunnerPlugin::class)

        val sharedIndexDir = buildDir.resolve("shared-index")

        val setupTask = registerSetupSharedIndex(sharedIndexDir)
        val generateSharedIndexTask = registerGenerateSharedIndexTask(sharedIndexDir) {
            dependsOn(setupTask)
        }
        registerCdnLayoutTask(sharedIndexDir) {
            dependsOn(generateSharedIndexTask)
        }
    }

    private fun Project.registerSetupSharedIndex(sharedIndexDir: File): TaskProvider<Task> {
        return tasks.register("setupSharedIndex") {
            group = "intellij shared index"

            sharedIndexDir.mkdir()

            val ideConfigDir = sharedIndexDir.resolve("ide-config").apply { mkdir() }
            val ideLogDir = sharedIndexDir.resolve("ide-log").apply { mkdir() }
            val ideSystem = sharedIndexDir.resolve("ide-system").apply { mkdir() }
            val ideProperties = sharedIndexDir.resolve("ide.properties")
            sharedIndexDir.resolve("generate-output").apply { mkdir() }

            ideProperties.createNewFile()
            val propertyFile = IntellijSharedIndexPlugin::class.java.getResourceAsStream("/shared-index/win/ide.properties")
            propertyFile?.copyTo(ideProperties.outputStream())
            // language=properties
            ideProperties.appendText("""
                idea.system.path=${ideSystem.absolutePath.escapePath()}
                idea.config.path=${ideConfigDir.absolutePath.escapePath()}
                idea.log.path=${ideLogDir.absolutePath.escapePath()}
            """.trimIndent())
        }
    }

    /**
     * <IDE command-line launcher> dump-shared-index project
     *   --output=<temp>/generate-output
     *   --tmp=<temp>/temp
     *   --project-dir=<path>
     *   --project-id=<project-name>
     *   --commit=<Git HEAD>
     */
    private fun Project.registerGenerateSharedIndexTask(sharedIndexDir: File, block: JavaExec.() -> Unit = {}): TaskProvider<JavaExec> {
        val intellijRunnerConfiguration: Configuration = configurations.getByName("intellijRunner")

        return tasks.register<JavaExec>("generateSharedIndex") {
            group = "intellij"
            mainClass.set("com.intellij.idea.Main")
            classpath = files(intellijRunnerConfiguration.resolve())

            environment.put("IDEA_PROPERTIES", "")

            args = listOf(
                "dump-shared-index",
                "project"
            )

            block()
        }

    }


    /**  <cdn-layout-tool> --indexes-dir=<dir> --url=<file storage root URL> */
    private fun Project.registerCdnLayoutTask(sharedIndexDir: File, block: JavaExec.() -> Unit = {}): TaskProvider<JavaExec> {
        val cdnLayout: Configuration = configurations.create("cdnLayout").apply {
            isVisible = false
        }
        cdnLayout.dependencies.add(dependencies.create("com.jetbrains.intellij.indexing.shared:cdn-layout-tool:0.8.60"))
        return tasks.register<JavaExec>("cdnLayout") {
            group = "intellij shared index"

            mainClass.set("org.jetbrains.intellij.sharedIndexes.local.MainKt")

            classpath = files(cdnLayout.resolve())

            args = listOf(
                "--indexes-dir=${sharedIndexDir.path}",
                "--url=https://example.com"
            )

            block()
        }
    }

    private fun String.escapePath(): String = replace("\\", "/")
}
