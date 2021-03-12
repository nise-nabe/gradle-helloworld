pluginManagement {
    repositories {
        maven {
            name = "Spring Plugin Release Repository"
            url = uri("https://repo.spring.io//plugins-release/")
            mavenContent {
                releasesOnly()
                includeGroup("org.springframework.boot")
            }
        }

        maven {
            name = "Spring Plugin Snapshot Repository"
            url = uri("https://repo.spring.io//plugins-snapshot/")
            mavenContent {
                snapshotsOnly()
                includeGroup("org.springframework.boot")
            }
        }

        gradlePluginPortal()
    }
}

rootProject.name = "buildSrc"
