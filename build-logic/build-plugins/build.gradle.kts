plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(project(":java-plugins"))
    implementation(project(":kotlin-plugins"))
    implementation(project(":kotlin-dsl-plugins"))

    implementation("gradle.plugin.org.jetbrains.gradle.plugin.idea-ext:gradle-idea-ext:1.1.7")
    implementation("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.22.0")
}
