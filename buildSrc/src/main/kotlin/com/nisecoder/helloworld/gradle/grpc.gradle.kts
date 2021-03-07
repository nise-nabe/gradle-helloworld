package com.nisecoder.helloworld.gradle

import com.google.protobuf.gradle.ExecutableLocator

plugins {
    id("com.google.protobuf")
    `java-library`
    idea
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.15.5"
    }
    plugins {
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java"
        }
    }
}
