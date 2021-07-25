plugins {
    `java-library`
    id("com.nisecoder.helloworld.gradle.build-basic")
    id("com.nisecoder.helloworld.gradle.kotlin")
    `maven-publish`
}

version = "1.1-SNAPSHOT"

dependencies {
    testImplementation(kotlin("test-junit5"))
}

tasks.test {
    useJUnitPlatform()
}

publishing {
    repositories {
        maven {
            name = "HelloGithubPackage"
            url = uri("https://maven.pkg.github.com/nise-nabe/gradle-helloworld")
            credentials(PasswordCredentials::class)
        }
    }

    publications {
        register<MavenPublication>("gpr") {
            from(components["java"])
        }
    }
}
