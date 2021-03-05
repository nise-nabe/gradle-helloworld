plugins {
    `java-library`
    id("com.nisecoder.gradle.kotlin")
    id("com.nisecoder.gradle.springboot")
}

dependencies {
    api("org.springframework:spring-web")
}
