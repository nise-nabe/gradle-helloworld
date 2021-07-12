plugins {
    java
    id("com.nisecoder.helloworld.gradle.build-basic")
    id("com.nisecoder.helloworld.gradle.kotlin")
}

repositories {
    mavenCentral()
    exclusiveContent {
        forRepository {
            maven {
                name = "HelloGithubPackage"
                url = uri("https://maven.pkg.github.com/nise-nabe/gradle-helloworld")
                // %HOMEPATH%/.gradle/gradle.properties
                credentials(PasswordCredentials::class)
            }
        }
        filter {
            includeModule("com.nisecoder.helloworld", "hello-github-packages")
        }
    }
}

dependencies {
    implementation("com.nisecoder.helloworld:hello-github-packages:1.0-SNAPSHOT")
}
