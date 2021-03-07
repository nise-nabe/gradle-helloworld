plugins {
    id("com.nisecoder.helloworld.gradle.grpc")
}

dependencies {
    implementation(platform("com.nisecoder.helloworld:platform-grpc"))
    api("com.google.protobuf:protobuf-java")
}
