plugins {
    java
    id("com.nisecoder.helloworld.gradle.build-basic")
    id("com.nisecoder.helloworld.gradle.kotlin")
    id("com.nisecoder.helloworld.gradle.springboot")
}

dependencies {
    implementation("io.github.microutils:kotlin-logging-jvm:3.0.4")

    kapt("org.springframework.boot:spring-boot-autoconfigure-processor")
    implementation("org.springframework.boot:spring-boot-starter-actuator")

    implementation("org.springframework.boot:spring-boot-starter-web") {
        exclude(group = "org.springframework.boot", module = "spring-boot-starter-tomcat")
    }
    implementation("org.springframework.boot:spring-boot-starter-jetty")
    implementation(platform("io.micrometer:micrometer-tracing-bom:1.0.0"))
    implementation("io.micrometer:micrometer-tracing")


    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
}


tasks.bootJar {
    launchScript()
}

tasks.test {
    useJUnitPlatform()
}
