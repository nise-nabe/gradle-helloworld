package com.nisecoder.helloworld.gradle.intellij

import com.nisecoder.helloworld.gradle.intellij.task.IntellijRunnerTask
import org.gradle.api.Plugin
import org.gradle.api.Project
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

        val generateSharedIndexTask = registerGenerateSharedIndexTask(sharedIndexDir)
        registerCdnLayoutTask(sharedIndexDir) {
            dependsOn(generateSharedIndexTask)
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
    private fun Project.registerGenerateSharedIndexTask(
        sharedIndexDir: File,
        block: JavaExec.() -> Unit = {}
    ): TaskProvider<out JavaExec> {

        return tasks.register<IntellijRunnerTask>("generateSharedIndex") {
            intellijRunnerConfiguration = configurations.getByName("intellijRunner")

            ideaPropertiesFile = sharedIndexDir.resolve("ide.properties")

            args = listOf(
                "dump-shared-index",
                "project",

                "--output=${sharedIndexDir.absolutePath}\\generate-output",
                "--tmp=${sharedIndexDir.absolutePath}\\temp",
                "--project-dir=${projectDir.absolutePath}",
                "--project-id=${project.name}",
                "--commit=-",
            )

            block()
        }

    }


    /**  <cdn-layout-tool> --indexes-dir=<dir> --url=<file storage root URL> */
    private fun Project.registerCdnLayoutTask(sharedIndexDir: File, block: JavaExec.() -> Unit = {}): TaskProvider<JavaExec> {
        val cdnLayout: Configuration = configurations.create("cdnLayout").apply {
            isVisible = false
        }
//        cdnLayout.dependencies.add(dependencies.create("com.jetbrains.intellij.indexing.shared:cdn-layout-tool:0.8.66"))
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
}
