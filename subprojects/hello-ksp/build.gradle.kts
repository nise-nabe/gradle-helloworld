plugins {
    id("com.nisecoder.helloworld.gradle.build-basic")
    id("com.nisecoder.helloworld.gradle.kotlin")
}

dependencies {
    implementation("com.google.devtools.ksp:symbol-processing-api:1.6.21-1.0.6")
}
