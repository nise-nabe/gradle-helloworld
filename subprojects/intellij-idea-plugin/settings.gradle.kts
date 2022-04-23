pluginManagement {
    repositories {
        gradlePluginPortal()
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
    }
}

rootProject.name = "intellij-idea-plugin"


include("hello-intellij-idea-plugin-simple")
include("hello-intellij-idea-plugin-new-project-wizard")
include("hello-intellij-idea-plugin-new-framework")
include("hello-intellij-idea-plugin-tool-window")
