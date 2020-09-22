pluginManagement {
    repositories {
        gradlePluginPortal()
    }
}

rootProject.name = "buildSrc"


for (project in rootProject.children) {
    project.projectDir = file("subprojects/${project.name}")
}
