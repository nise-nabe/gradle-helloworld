import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.plugin.SpringBootPlugin

plugins {
    java
    kotlin("jvm")
    id("com.nisecoder.gradle.springboot")
}

dependencies {
    implementation(platform(SpringBootPlugin.BOM_COORDINATES))
    developmentOnly(platform(SpringBootPlugin.BOM_COORDINATES))
    kapt(platform(SpringBootPlugin.BOM_COORDINATES))


    kapt("org.springframework.boot:spring-boot-autoconfigure-processor")
    implementation("org.springframework.boot:spring-boot-starter-actuator")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
}

tasks.bootJar {
    launchScript()
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
}
