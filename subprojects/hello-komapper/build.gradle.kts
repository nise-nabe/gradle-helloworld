plugins {
    java
    id("com.nisecoder.helloworld.gradle.build-basic")
    id("com.nisecoder.helloworld.gradle.kotlin")
    id("com.google.devtools.ksp")
}

dependencies {
    implementation(platform("org.komapper:komapper-platform:1.1.2"))
    ksp(platform("org.komapper:komapper-platform:1.1.2"))
    implementation("org.komapper:komapper-starter-jdbc")
    implementation("org.komapper:komapper-dialect-h2-jdbc")
    ksp("org.komapper:komapper-processor")
}
