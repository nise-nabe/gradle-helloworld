import com.nisecoder.gradle.plugin.cidetect.isDrone
import com.nisecoder.gradle.plugin.cidetect.isGithubActions
import com.nisecoder.gradle.plugin.cidetect.isJenkins
import com.nisecoder.gradle.plugin.cidetect.isTeamCity

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
    implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:2.2.2")
    runtimeOnly("mysql:mysql-connector-java:8.0.29")

    testImplementation(platform("org.testcontainers:testcontainers-bom:1.17.3"))
    testImplementation("org.testcontainers:testcontainers")
    testImplementation("org.testcontainers:mysql")
    testImplementation("org.testcontainers:junit-jupiter")
    testImplementation("io.github.microutils:kotlin-logging-jvm:2.1.23")
}

tasks.test {
    useJUnitPlatform {

        if (isDrone || isJenkins || isTeamCity || (isGithubActions && osdetector.os != "linux")) {
            excludeTags("containers")
        }
    }
}
