package com.nisecoder.helloworld.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.plugins.ide.idea.model.IdeaModel

class BuildBasicPlugin: Plugin<Project> {
    override fun apply(project: Project): Unit = project.run {
        plugins.apply("org.jetbrains.gradle.plugin.idea-ext")

        extensions.configure<IdeaModel>("idea") {
            module {
                isDownloadJavadoc = true
                isDownloadSources = true
            }
        }
    }
}
