package com.nisecoder.helloworld.gradle

import com.google.protobuf.gradle.protobuf
import com.google.protobuf.gradle.protoc
import com.google.protobuf.gradle.plugins
import com.google.protobuf.gradle.id

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
            artifact = "io.grpc:protoc-gen-grpc-java:1.35.0"
        }
    }
}
