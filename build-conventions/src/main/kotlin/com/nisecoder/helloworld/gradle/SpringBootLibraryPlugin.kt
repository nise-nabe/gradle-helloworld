package com.nisecoder.helloworld.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.api.tasks.SourceSet
import org.gradle.api.tasks.SourceSetContainer
import org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES

class SpringBootLibraryPlugin: Plugin<Project> {
    override fun apply(project: Project): Unit = project.run {
        plugins.apply("java-library")
        plugins.apply(SpringBootConventionPlugin::class.java)

        extensions.configure<SourceSetContainer>("sourceSets") {
            val mainSourceSet = getByName(SourceSet.MAIN_SOURCE_SET_NAME)
            val mainOutput = objects.fileCollection().from(mainSourceSet.output)

            val springbootSourceSet = create("springboot") {
                compileClasspath += mainOutput
                runtimeClasspath += mainOutput
            }

            configurations.getByName(springbootSourceSet.implementationConfigurationName)
                .extendsFrom(configurations.getByName(mainSourceSet.implementationConfigurationName))
            configurations.getByName(springbootSourceSet.apiConfigurationName)
                .extendsFrom(configurations.getByName(mainSourceSet.apiConfigurationName))

        }

        extensions.configure<JavaPluginExtension>("java") {
            registerFeature("springboot") {
                usingSourceSet(sourceSets.getByName("springboot"))
            }
        }

        dependencies.add("api", dependencies.platform(BOM_COORDINATES))
        dependencies.add("springbootApi", dependencies.platform(BOM_COORDINATES))
        dependencies.add("springbootApi", "org.springframework.boot:spring-boot-starter")
    }
}
