package com.nisecoder.helloworld.gradle.intellij

import com.nisecoder.helloworld.gradle.intellij.task.IntellijRunnerTask
import com.nisecoder.helloworld.gradle.transform.LibDirectoryTransform
import com.nisecoder.helloworld.gradle.transform.UnzipTransform
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.artifacts.Configuration
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.artifacts.type.ArtifactTypeDefinition
import org.gradle.api.attributes.Attribute
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.TaskContainer
import org.gradle.api.tasks.TaskProvider
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.register
import org.gradle.kotlin.dsl.registerTransform
import java.io.File

class IntellijRunnerPlugin: Plugin<Project> {

    @Internal
    private val artifactType = Attribute.of("artifactType", String::class.java)

    override fun apply(project: Project): Unit = project.run {

        dependencies.registerIdeaBinaryTransform()

        val ideaRunnerConfiguration: Configuration = configurations.create("intellijRunner").also {
            it.dependencies.add(
                dependencies.create(
                    group = "com.jetbrains.intellij.idea",
                    name = "ideaIC",
                    version = "2022.1.1",
                    ext = "zip"
                )
            )

            it.attributes.attribute(artifactType, ArtifactTypeDefinition.JAR_TYPE)
        }

        val tmpDir = buildDir.resolve("intellij-runner").apply { mkdir() }

        val ideProperties = tmpDir.resolve("ide.properties")
        tasks.registerSetupIdeProperties(tmpDir, ideProperties)

        tasks.register<IntellijRunnerTask>("intellijRun") {
            intellijRunnerConfiguration = ideaRunnerConfiguration
        }

        tasks.register<IntellijRunnerTask>("intellijInspect") {
            intellijRunnerConfiguration = ideaRunnerConfiguration
            ideaPropertiesFile = ideProperties
            args = listOf(
                "inspect",
                // <project_file_path> <inspection_profile> <output_path>
                projectDir.path,
                projectDir.resolve(".idea/inspectionProfiles/Project_Default.xml").absolutePath,
                buildDir.resolve("intellij-runner/inspect").absolutePath
            )
        }
    }

    private fun DependencyHandler.registerIdeaBinaryTransform() {
        attributesSchema {
            attribute(artifactType)
        }
        registerTransform(UnzipTransform::class) {
            from.attribute(artifactType, ArtifactTypeDefinition.ZIP_TYPE)
            to.attribute(artifactType, ArtifactTypeDefinition.DIRECTORY_TYPE)
        }
        registerTransform(LibDirectoryTransform::class) {
            from.attribute(artifactType, ArtifactTypeDefinition.DIRECTORY_TYPE)
            to.attribute(artifactType, ArtifactTypeDefinition.JAR_TYPE)
        }
    }

    private fun TaskContainer.registerSetupIdeProperties(runnerDir: File, ideProperties: File): TaskProvider<Task> {
        return register("setupIdeProperties") {
            group = "intellij runner"

            val ideConfigDir = runnerDir.resolve("ide-config").apply { mkdir() }
            val ideLogDir = runnerDir.resolve("ide-log").apply { mkdir() }
            val ideSystem = runnerDir.resolve("ide-system").apply { mkdir() }

            ideProperties.createNewFile()
            val propertyFile = IntellijRunnerPlugin::class.java.getResourceAsStream("/shared-index/win/ide.properties")
            propertyFile?.copyTo(ideProperties.outputStream())
            // language=properties
            ideProperties.appendText("""
                idea.system.path=${ideSystem.absolutePath.escapePath()}
                idea.config.path=${ideConfigDir.absolutePath.escapePath()}
                idea.log.path=${ideLogDir.absolutePath.escapePath()}
            """.trimIndent())
        }
    }

    private fun String.escapePath(): String = replace("\\", "\\\\")
}
