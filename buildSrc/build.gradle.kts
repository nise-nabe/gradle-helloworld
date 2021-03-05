plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
}

dependencies {
    implementation("gradle.plugin.org.jetbrains.gradle.plugin.idea-ext:gradle-idea-ext:1.0")

    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.21")
    implementation("org.jetbrains.kotlin:kotlin-allopen:1.4.21")
    implementation("org.jetbrains.kotlin:kotlin-noarg:1.4.21")

    implementation("org.springframework.boot:spring-boot-gradle-plugin:2.4.3")

    implementation("com.diffplug.spotless:spotless-plugin-gradle:5.10.2")
}
