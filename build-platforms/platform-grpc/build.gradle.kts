plugins {
    `java-platform`
}

dependencies {
    constraints {
        api("com.google.protobuf:protoc:3.21.12")
        api("com.google.protobuf:protobuf-java:3.21.12")
        api("io.grpc:protoc-gen-grpc-java:1.51.1")
    }
}
