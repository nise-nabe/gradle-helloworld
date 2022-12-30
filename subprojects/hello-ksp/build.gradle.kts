plugins {
    id("com.nisecoder.helloworld.gradle.build-basic")
    id("com.nisecoder.helloworld.gradle.kotlin")
}

dependencies {
    implementation("com.google.devtools.ksp:symbol-processing-api:1.7.22-1.0.8")
}
