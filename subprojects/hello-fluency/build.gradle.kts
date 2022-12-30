import org.jetbrains.gradle.ext.packagePrefix
import org.jetbrains.gradle.ext.settings

plugins {
    id("com.nisecoder.helloworld.gradle.build-basic")
    kotlin("jvm")
}

dependencies {
    implementation("org.komamitsu:fluency-core:2.6.0")
    implementation("org.komamitsu:fluency-fluentd:2.6.0")

    implementation("org.slf4j:slf4j-api:2.0.5")
    implementation("ch.qos.logback:logback-classic:1.4.5")
}

idea {
    module {
        settings {
            packagePrefix["src/main/kotlin"] = "com.nisecoder.helloworld"
        }
    }
}
