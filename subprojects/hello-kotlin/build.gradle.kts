import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    kotlin("jvm")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))

    testImplementation(kotlin("test-junit5"))
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_14.toString()
    }
}

tasks.test {
    useJUnitPlatform()

    maxParallelForks = (Runtime.getRuntime().availableProcessors() / 2).takeIf { it > 0 } ?: 1
    reports {
        html.isEnabled = false
        junitXml.isEnabled = false
    }
}
