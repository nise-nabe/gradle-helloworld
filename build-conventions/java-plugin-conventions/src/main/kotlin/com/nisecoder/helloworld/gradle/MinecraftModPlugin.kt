package com.nisecoder.helloworld.gradle

import net.minecraftforge.gradle.userdev.UserDevExtension
import net.minecraftforge.gradle.userdev.UserDevPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.jvm.toolchain.JavaLanguageVersion
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure

class MinecraftModPlugin: Plugin<Project> {
    override fun apply(project: Project): Unit = project.run {
        plugins.apply(UserDevPlugin::class)

        extensions.configure<JavaPluginExtension> {
            toolchain {
                languageVersion.set(JavaLanguageVersion.of(16))
            }
        }

        extensions.configure<UserDevExtension> {
            mappings("official", "1.17.1")
        }

        dependencies.add("minecraft", "net.minecraftforge:forge:1.17.1-37.0.27")
    }
}
