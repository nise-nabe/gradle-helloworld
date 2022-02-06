package com.nisecoder.helloworld.gradle

import com.github.gradle.node.NodePlugin
import com.nisecoder.helloworld.gradle.nuxt.NuxtBuildTask
import com.nisecoder.helloworld.gradle.nuxt.NuxtExtension
import com.nisecoder.helloworld.gradle.nuxt.NuxtGenerateTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.BasePlugin
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.register

@Suppress("unused")
class NuxtPlugin: Plugin<Project> {
    override fun apply(project: Project): Unit = project.run {
        plugins.apply(BasePlugin::class)
        plugins.apply(NodePlugin::class)

        val nuxt = extensions.create<NuxtExtension>("nuxt").apply {
            val baseDir = project.layout.projectDirectory
            src {
                assets.convention(baseDir.dir("assets"))
                components.convention(baseDir.dir("components"))
                content.convention(baseDir.dir("content"))
                layouts.convention(baseDir.dir("layouts"))
                middleware.convention(baseDir.dir("middleware"))
                modules.convention(baseDir.dir("modules"))
                pages.convention(baseDir.dir("pages"))
                plugins.convention(baseDir.dir("plugins"))
                static.convention(baseDir.dir("static"))
                store.convention(baseDir.dir("store"))
            }
            nuxtBuild.convention(baseDir.dir(".nuxt"))
            dist.convention(baseDir.dir("dist"))
            configFile.convention(baseDir.file("nuxt.config.js"))
        }

        val yarnTask = tasks.getByName("yarn")

        val nuxtBuildTask = tasks.register<NuxtBuildTask>("nuxtBuild") {
            src.set(nuxt.src)
            outputDir.convention(nuxt.nuxtBuild)
            dependsOn(yarnTask)
        }

        val nuxtGenerateTask = tasks.register<NuxtGenerateTask>("nuxtGenerate") {
            src.set(nuxt.src)
            outputDir.convention(nuxt.dist)
            dependsOn(yarnTask)
        }

        tasks.named("build") {
            dependsOn(nuxtBuildTask)
        }

        tasks.named("assemble") {
            dependsOn(nuxtGenerateTask)
        }
    }
}
