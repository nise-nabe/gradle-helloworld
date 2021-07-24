import com.nisecoder.helloworld.gradle.idea.ext.packagePrefix
import com.nisecoder.helloworld.gradle.idea.ext.settings

plugins {
    id("com.nisecoder.helloworld.gradle.build-basic")
    kotlin("jvm")
}

dependencies {
    implementation("org.komamitsu:fluency-core:2.6.0")
    implementation("org.komamitsu:fluency-fluentd:2.6.0")

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
