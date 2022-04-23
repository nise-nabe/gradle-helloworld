dependencyResolutionManagement {
    @Suppress("UnstableApiUsage")
    repositories {
        mavenCentral()
        exclusiveContent {
            forRepository {
                maven {
                    url = uri("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
                }
            }

            filter {
                includeGroup("org.spigotmc")
                includeGroup("org.bukkit")
            }
        }
    }
}

rootProject.name = "minecraft-forge"

include("hello-minecraft-spigot-mod")
