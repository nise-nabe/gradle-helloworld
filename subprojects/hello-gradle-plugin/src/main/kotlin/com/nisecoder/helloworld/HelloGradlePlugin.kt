package com.nisecoder.helloworld

import org.gradle.api.Plugin
import org.gradle.api.Project

class HelloGradlePlugin: Plugin<Project> {
    override fun apply(project: Project): Unit = project.run {
        logger.lifecycle("Hello, World!")
    }
}
