package com.nisecoder.helloworld.gradle

import com.nisecoder.helloworld.gradle.transform.LibDirectoryTransform
import com.nisecoder.helloworld.gradle.transform.UnzipTransform
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.Configuration
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.artifacts.type.ArtifactTypeDefinition
import org.gradle.api.attributes.Attribute
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.JavaExec
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.register
import org.gradle.kotlin.dsl.registerTransform

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
                    version = "2021.2.1",
                    ext = "zip"
                )
            )

            it.attributes.attribute(artifactType, ArtifactTypeDefinition.JAR_TYPE)
        }

        tasks.register<JavaExec>("intellijRun") {
            group = "intellij"
            mainClass.set("com.intellij.idea.Main")
            classpath = files(ideaRunnerConfiguration.resolve())
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
}
