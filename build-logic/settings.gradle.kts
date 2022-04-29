@file:Suppress("UnstableApiUsage")

pluginManagement {
    repositories {
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        maven {
            name = "Spring Release Repository"
            url = uri("https://repo.spring.io/release")
            mavenContent {
                releasesOnly()
                includeGroup("org.springframework")
                includeGroup("org.springframework.boot")
            }
        }

        maven {
            name = "Spring Snapshot Repository"
            url = uri("https://repo.spring.io/snapshot")
            mavenContent {
                snapshotsOnly()
                includeGroup("org.springframework")
                includeGroup("org.springframework.boot")
            }
        }

        exclusiveContent {
            forRepository {
                maven {
                    url = uri("https://packages.jetbrains.team/maven/p/ij/intellij-shared-indexes-public/")
                }
            }
            filter {
                includeGroup("com.jetbrains.intellij.indexing.shared")
            }
        }

        exclusiveContent {
            forRepository {
                maven {
                    url = uri("https://cache-redirector.jetbrains.com/intellij-repository/releases")
                    metadataSources {
                        mavenPom()
                    }
                    mavenContent {
                        releasesOnly()
                    }
                }
            }
            filter {
                includeGroup("com.jetbrains.intellij.idea")
                includeGroup("com.jetbrains.intellij.java")
            }
        }

        gradlePluginPortal()

        google()

        exclusiveContent {
            forRepository {
                maven {
                    url = uri("https://maven.minecraftforge.net")
                }
            }
            filter {
                includeGroup("net.minecraftforge.gradle")
                includeGroup("net.minecraftforge")
            }
        }
    }

    versionCatalogs {
        create("parentLibs") {
            from(files("../gradle/libs.versions.toml"))
        }
    }
}

rootProject.name = "build-logic"

include("kotlin-dsl-plugins")
include("java-plugins")
include("spring-boot-plugins")
