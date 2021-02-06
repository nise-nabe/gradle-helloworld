plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
}

dependencies {
    implementation("gradle.plugin.org.jetbrains.gradle.plugin.idea-ext:gradle-idea-ext:0.10")

    implementation(platform("org.jetbrains.kotlin:kotlin-bom:1.4.30"))
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin")
    implementation("org.jetbrains.kotlin:kotlin-allopen")
    implementation("org.jetbrains.kotlin:kotlin-noarg")

    implementation("org.springframework.boot:spring-boot-gradle-plugin:2.4.2")

    implementation("com.diffplug.spotless:spotless-plugin-gradle:5.9.0")
}
