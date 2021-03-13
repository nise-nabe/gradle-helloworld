plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
}

dependencies {
    implementation("gradle.plugin.org.jetbrains.gradle.plugin.idea-ext:gradle-idea-ext:1.0")

    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin")
    implementation("org.jetbrains.kotlin:kotlin-allopen")
    implementation("org.jetbrains.kotlin:kotlin-noarg")

    implementation("org.springframework.boot:spring-boot-gradle-plugin:2.4.3")

    implementation("com.diffplug.spotless:spotless-plugin-gradle:5.10.2")

    implementation("com.google.protobuf:protobuf-gradle-plugin:0.8.15")
}

kotlinDslPluginOptions {
    experimentalWarning.set(false)
}
