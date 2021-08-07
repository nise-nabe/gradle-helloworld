@file:Suppress("UnstableApiUsage")

enableFeaturePreview("VERSION_CATALOGS")

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
        create("lib") {
            from(files("../gradle/libs.versions.toml"))
        }
    }
}

rootProject.name = "build-conventions"

include("kotlin-dsl-conventions")
include("spring-boot-conventions")
