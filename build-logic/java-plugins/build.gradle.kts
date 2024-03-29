plugins {
    `java-gradle-plugin`
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.0")
}

gradlePlugin {
    plugins {
        register("kotlin") {
            id = "com.nisecoder.helloworld.gradle.kotlin"
            implementationClass = "com.nisecoder.helloworld.gradle.KotlinPlugin"
        }
    }
}
