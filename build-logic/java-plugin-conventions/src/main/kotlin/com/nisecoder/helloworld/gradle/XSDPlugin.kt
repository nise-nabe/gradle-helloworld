package com.nisecoder.helloworld.gradle

import com.github.bjornvester.xjc.XjcPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaLibraryPlugin
import org.gradle.kotlin.dsl.apply

class XSDPlugin: Plugin<Project> {
    override fun apply(project: Project): Unit = project.run {
        plugins.apply(JavaLibraryPlugin::class)
        plugins.apply(XjcPlugin::class)
    }
}
