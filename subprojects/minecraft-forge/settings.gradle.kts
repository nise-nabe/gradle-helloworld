dependencyResolutionManagement {
    @Suppress("UnstableApiUsage")
    repositories {
        mavenCentral()
        exclusiveContent {
            forRepository {
                maven {
                    url = uri("https://libraries.minecraft.net/")
                    metadataSources {
                        artifact()
                    }
                }
            }

            filter {
                includeGroup("net.minecraft")
            }
        }
        exclusiveContent {
            forRepository {
                maven {
                    url = uri("https://maven.minecraftforge.net/")
                }
            }

            filter {
                includeGroup("cpw.mods")
                includeGroup("net.minecraftforge")
                includeGroup("net.jodah")
            }
        }
    }
}

rootProject.name = "minecraft-forge"

include("hello-minecraft-forge-mod")
