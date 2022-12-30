plugins {
    `java-library`
    id("com.nisecoder.helloworld.gradle.build-basic")
    id("com.nisecoder.helloworld.gradle.grpc")
}

dependencies {
    api("com.google.protobuf:protobuf-java:3.21.12")
}
