import com.nisecoder.helloworld.gradle.idea.ext.packagePrefix
import com.nisecoder.helloworld.gradle.idea.ext.settings

plugins {
    id("com.nisecoder.helloworld.gradle.build-basic")
    kotlin("jvm")
}

dependencies {
    implementation("org.komamitsu:fluency-core:2.6.0")
    implementation("org.komamitsu:fluency-fluentd:2.6.0")

    implementation("org.slf4j:slf4j-api:1.7.30")
    implementation("ch.qos.logback:logback-classic:1.2.5")
}

idea {
    module {
        settings {
            packagePrefix {
                this["src/main/kotlin"] = "com.nisecoder.helloworld"
            }
        }
    }
}
