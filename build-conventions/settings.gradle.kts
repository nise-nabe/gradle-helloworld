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

        google()

        gradlePluginPortal()
    }

    versionCatalogs {
        create("lib") {
            from(files("../gradle/libs.versions.toml"))
        }
    }
}

rootProject.name = "build-conventions"

enableFeaturePreview("VERSION_CATALOGS")
