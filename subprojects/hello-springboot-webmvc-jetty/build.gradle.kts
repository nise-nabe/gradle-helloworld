import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    id("com.nisecoder.gradle.kotlin")
    id("com.nisecoder.gradle.springboot")
}

dependencies {
    kapt("org.springframework.boot:spring-boot-autoconfigure-processor")
    implementation("org.springframework.boot:spring-boot-starter-actuator")

    implementation("org.springframework.boot:spring-boot-starter-web") {
        exclude(group = "org.springframework.boot", module = "spring-boot-starter-tomcat")
    }
    implementation("org.springframework.boot:spring-boot-starter-jetty")


    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
}


tasks.bootJar {
    launchScript()
}

tasks.test {
    useJUnitPlatform()
}
