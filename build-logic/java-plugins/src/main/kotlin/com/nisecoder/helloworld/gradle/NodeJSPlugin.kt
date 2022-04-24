package com.nisecoder.helloworld.gradle

import com.github.gradle.node.NodePlugin
import com.github.gradle.node.yarn.task.YarnTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.BasePlugin
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.named

class NodeJSPlugin: Plugin<Project> {
    override fun apply(project: Project): Unit = project.run {
        plugins.apply(BasePlugin::class)
        plugins.apply(NodePlugin::class)

        tasks.named<YarnTask>("yarn") {
            if ((System.getenv("CI") ?: "") == "true") {
                args.add("--immutable")
            }
        }
    }
}
