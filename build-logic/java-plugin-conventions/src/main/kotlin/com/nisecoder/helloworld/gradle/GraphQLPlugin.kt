package com.nisecoder.helloworld.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project

class GraphQLPlugin: Plugin<Project> {
    override fun apply(project: Project): Unit = project.run {
        //plugins.apply("com.netflix.dgs.codegen")
    }
}
