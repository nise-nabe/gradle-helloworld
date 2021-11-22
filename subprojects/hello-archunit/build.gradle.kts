plugins {
    application
    `jvm-test-suite`
    id("com.nisecoder.helloworld.gradle.build-basic")
    id("com.nisecoder.helloworld.gradle.kotlin")
}

testing {
    suites {
        val `arch-test` by registering(JvmTestSuite::class) {
            dependencies {
                implementation("com.tngtech.archunit:archunit:0.22.0")
            }
        }
    }
}
