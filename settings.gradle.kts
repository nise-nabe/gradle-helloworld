pluginManagement {
    repositories {
        gradlePluginPortal()
    }

    plugins {
        val kotlinVersion = "1.4.10"
        kotlin("jvm") version kotlinVersion
        kotlin("js") version kotlinVersion
        kotlin("kapt") version kotlinVersion
        kotlin("plugin.spring") version kotlinVersion
        kotlin("plugin.noarg") version kotlinVersion

        id("org.springframework.boot") version "2.3.6.RELEASE"

        id("com.diffplug.spotless") version "5.6.1"
    }
}

rootProject.name = "HelloWorld"

include("hello-kotlin")
include("hello-typescript")
include("hello-springboot")
include("hello-httpclient")

for (project in rootProject.children) {
    project.projectDir = file("subprojects/${project.name}")
}
