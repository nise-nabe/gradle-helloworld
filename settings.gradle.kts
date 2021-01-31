pluginManagement {
    repositories {
        gradlePluginPortal()
    }

    plugins {
        val kotlinVersion = "1.4.20"
        kotlin("jvm") version kotlinVersion
        kotlin("js") version kotlinVersion
        kotlin("kapt") version kotlinVersion
        kotlin("plugin.spring") version kotlinVersion
        kotlin("plugin.noarg") version kotlinVersion

        id("org.springframework.boot") version "2.4.1"

        id("com.diffplug.spotless") version "5.8.2"
    }
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}

rootProject.name = "HelloWorld"

include("hello-kotlin")
include("hello-typescript")
include("hello-springboot")
include("hello-springboot-webmvc-tomcat")
include("hello-springboot-webmvc-jetty")
include("hello-httpclient")

for (project in rootProject.children) {
    project.projectDir = file("subprojects/${project.name}")
}
