plugins {
    `java-library`
    id("com.nisecoder.helloworld.gradle.build-basic")
    id("com.nisecoder.helloworld.gradle.grpc-resolvable")
}

dependencies {
    implementation(platform("com.nisecoder.helloworld:platform-grpc"))
    api("com.google.protobuf:protobuf-java")
}
