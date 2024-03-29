import com.nisecoder.gradle.plugin.cidetect.isDrone
import com.nisecoder.gradle.plugin.cidetect.isGithubActions
import com.nisecoder.gradle.plugin.cidetect.isJenkins
import com.nisecoder.gradle.plugin.cidetect.isTeamCity
import com.nisecoder.gradle.plugin.cidetect.isBuddy

plugins {
    java
    id("com.nisecoder.helloworld.gradle.kotlin")
    id("com.nisecoder.helloworld.gradle.springboot")
    id("com.nisecoder.ci-detect")
    id("com.google.osdetector")
    kotlin("plugin.spring")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:3.0.1")
    runtimeOnly("mysql:mysql-connector-java:8.0.31")

    testImplementation(platform("org.testcontainers:testcontainers-bom:1.17.3"))
    testImplementation("org.testcontainers:testcontainers")
    testImplementation("org.testcontainers:mysql")
    testImplementation("org.testcontainers:junit-jupiter")
    testImplementation("io.github.microutils:kotlin-logging-jvm:3.0.4")
}

tasks.test {
    useJUnitPlatform {

        if (isDrone || isJenkins || isTeamCity || isBuddy || (isGithubActions && osdetector.os != "linux")) {
            excludeTags("containers")
        }
    }
}
