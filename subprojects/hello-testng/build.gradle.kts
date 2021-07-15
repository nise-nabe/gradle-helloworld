plugins {
    `java-library`
    id("com.nisecoder.helloworld.gradle.build-basic")
    id("com.nisecoder.helloworld.gradle.kotlin")
}

tasks.test {
    useTestNG()
}

dependencies {
    testImplementation("org.testng:testng:7.4.0")
    testImplementation(kotlin("test"))
}
