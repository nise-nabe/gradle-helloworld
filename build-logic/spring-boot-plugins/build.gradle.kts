plugins {
    `kotlin-dsl-base`
    `java-gradle-plugin`
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin")
    implementation("org.jetbrains.kotlin:kotlin-allopen")
    implementation("org.jetbrains.kotlin:kotlin-noarg")

    implementation("org.springframework.boot:spring-boot-gradle-plugin:2.7.6") {
        because("springboot convention plugins")
    }
}

kotlin {
    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
        vendor.set(JvmVendorSpec.ADOPTIUM)
    }
}

gradlePlugin {
    plugins {
        register("com.nisecoder.helloworld.gradle.springboot") {
            id = "com.nisecoder.helloworld.gradle.springboot"
            implementationClass = "com.nisecoder.helloworld.gradle.SpringBootApplicationPlugin"
        }
        register("com.nisecoder.helloworld.gradle.springboot-lib") {
            id = "com.nisecoder.helloworld.gradle.springboot-lib"
            implementationClass = "com.nisecoder.helloworld.gradle.SpringBootLibraryPlugin"
        }
    }
}
