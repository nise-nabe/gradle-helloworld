plugins {
    java
    id("com.nisecoder.helloworld.gradle.build-basic")
    id("com.nisecoder.helloworld.gradle.kotlin")
    id("com.nisecoder.helloworld.gradle.springboot")
}

dependencies {
    implementation(platform("org.springframework.cloud:spring-cloud-dependencies:2020.0.+"))
    implementation("io.github.microutils:kotlin-logging-jvm:2.0.10")

    kapt("org.springframework.boot:spring-boot-autoconfigure-processor")
    implementation("org.springframework.boot:spring-boot-starter-actuator")

    implementation("org.springframework.boot:spring-boot-starter-web") {
        exclude(group = "org.springframework.boot", module = "spring-boot-starter-tomcat")
    }
    implementation("org.springframework.boot:spring-boot-starter-jetty")
    implementation("org.springframework.cloud:spring-cloud-starter-sleuth")


    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
}


tasks.bootJar {
    launchScript()
}

tasks.test {
    useJUnitPlatform()
}
