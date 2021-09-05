package com.nisecoder.helloworld.gradle.intellij.task

import org.gradle.api.artifacts.Configuration
import org.gradle.api.file.ProjectLayout
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.JavaExec
import java.io.File
import javax.inject.Inject

abstract class IntellijRunnerTask @Inject constructor(
    private val projectLayout: ProjectLayout
): JavaExec() {
    @get:Internal
    lateinit var intellijRunnerConfiguration: Configuration

    @get:Internal
    lateinit var ideaPropertiesFile:  File

    init {
        group = "intellij runner"
        mainClass.set("com.intellij.idea.Main")
    }

    override fun exec() {
        jvmArgs = listOf(
            "-Xmx8G",
            "-Didea.properties.file=${ideaPropertiesFile.absolutePath}",
            "-Djava.system.class.loader=com.intellij.util.lang.PathClassLoader",
            "-Didea.vendor.name=JetBrains",
            "-Didea.paths.selector=IdeaIC2021.2",
            "-Didea.platform.prefix=Idea",
            "-Didea.jre.check=true",
            "-Dsplash=true"
        )
        classpath = projectLayout.files(intellijRunnerConfiguration.resolve())
        super.exec()
    }
}
