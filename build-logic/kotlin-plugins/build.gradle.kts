import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    idea
    `kotlin-dsl-base`
    `java-gradle-plugin`
}

kotlin {
    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(19))
        vendor.set(JvmVendorSpec.ADOPTIUM)
    }
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        languageVersion = "1.8"
        apiVersion = "1.8"
        javaParameters = true
    }
}

idea {
    module {
        isDownloadJavadoc = true
        isDownloadSources = true
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.0")
    implementation("org.jetbrains.kotlin:kotlin-allopen:1.8.0")
    implementation("org.jetbrains.kotlin:kotlin-noarg:1.8.0")

    implementation("com.diffplug.spotless:spotless-plugin-gradle:6.13.0")

    implementation("gradle.plugin.org.jetbrains.gradle.plugin.idea-ext:gradle-idea-ext:1.1.7")
    implementation("org.jetbrains.intellij.plugins:gradle-intellij-plugin:1.11.0")
    implementation("org.jetbrains.intellij.plugins:gradle-changelog-plugin:1.3.1")

    implementation("com.jetbrains.intellij.indexing.shared:cdn-layout-tool:0.8.66")

    implementation("com.google.devtools.ksp:com.google.devtools.ksp.gradle.plugin:1.8.0-1.0.8")
}

gradlePlugin {
    plugins {
        register("intellij-gradle-plugin") {
            id = "com.nisecoder.helloworld.gradle.intellij-gradle-plugin"
            implementationClass = "com.nisecoder.helloworld.gradle.intellij.IntellijGradlePlugin"
        }
        register("intellij-runner") {
            id = "com.nisecoder.helloworld.gradle.intellij-runner"
            implementationClass = "com.nisecoder.helloworld.gradle.intellij.IntellijRunnerPlugin"
        }
        register("shared-index") {
            id = "com.nisecoder.helloworld.gradle.shared-index"
            implementationClass = "com.nisecoder.helloworld.gradle.intellij.IntellijSharedIndexPlugin"
        }
    }
}
