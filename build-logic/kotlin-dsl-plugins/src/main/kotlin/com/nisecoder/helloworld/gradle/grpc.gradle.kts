package com.nisecoder.helloworld.gradle

plugins {
    id("com.google.protobuf")
    java
    idea
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.17.3"
    }
    plugins {
        register("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:1.39.0"
        }
    }
}
