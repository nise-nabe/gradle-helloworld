plugins {
    java
    id("com.nisecoder.helloworld.gradle.build-basic")
    id("com.nisecoder.helloworld.gradle.kotlin")
}

dependencies {
    implementation("com.nisecoder.helloworld:hello-github-packages:1.0")
}
