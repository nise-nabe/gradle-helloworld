package com.nisecoder.helloworld.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.jetbrains.intellij.IntelliJPlugin

class IntellijGradlePlugin: Plugin<Project> {
    override fun apply(project: Project): Unit = project.run {
        plugins.apply(IntelliJPlugin::class)
    }
}
