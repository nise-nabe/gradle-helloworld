import com.nisecoder.gradle.plugin.cidetect.isDrone
import com.nisecoder.gradle.plugin.cidetect.isJenkins
import com.nisecoder.gradle.plugin.cidetect.isTeamCity

plugins {
    java
    id("com.nisecoder.helloworld.gradle.kotlin")
    id("com.nisecoder.helloworld.gradle.springboot")
    id("com.nisecoder.ci-detect")
    kotlin("plugin.spring")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:2.+")
    runtimeOnly("mysql:mysql-connector-java:8.0.+")

    testImplementation("org.testcontainers:testcontainers:1.+")
    testImplementation("org.testcontainers:mysql:1.+")
    testImplementation("org.testcontainers:junit-jupiter:1.+")
}

tasks.test {
    useJUnitPlatform {
        if (isDrone || isJenkins || isTeamCity) {
            excludeTags("containers")
        }
    }
}
