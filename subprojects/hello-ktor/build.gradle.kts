plugins {
    application
    id("com.nisecoder.helloworld.gradle.build-basic")
    id("com.nisecoder.helloworld.gradle.kotlin")
}

application {
    mainClass.set("io.ktor.server.netty.EngineMain")
}

dependencies {
    implementation("io.ktor:ktor-server-core:1.6.1")
    implementation("io.ktor:ktor-server-netty:1.6.1")
    implementation("ch.qos.logback:logback-classic:1.2.5")

    testImplementation(kotlin("test-junit5"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.7.2")
    testImplementation("io.ktor:ktor-server-test-host:1.6.1")
}

tasks.test {
    useJUnitPlatform()
}
