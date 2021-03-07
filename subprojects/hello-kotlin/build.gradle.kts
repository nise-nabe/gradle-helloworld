plugins {
    java
    id("com.nisecoder.helloworld.gradle.kotlin")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))

    testImplementation(kotlin("test-junit5"))
}

tasks.test {
    useJUnitPlatform()

    maxParallelForks = (Runtime.getRuntime().availableProcessors() / 2).takeIf { it > 0 } ?: 1
    reports {
        html.isEnabled = false
        junitXml.isEnabled = false
    }
}
