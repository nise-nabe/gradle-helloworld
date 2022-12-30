plugins {
    java
    id("com.nisecoder.helloworld.gradle.build-basic")
}

dependencies {
    implementation("org.gradle:gradle-tooling-api:7.4.2")
    runtimeOnly("org.slf4j:slf4j-simple:2.0.5")
}
