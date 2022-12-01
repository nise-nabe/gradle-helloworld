plugins {
    `java-platform`
}

dependencies {
    constraints {
        api("com.google.protobuf:protoc:3.15.5")
        api("com.google.protobuf:protobuf-java:3.21.10")
        api("io.grpc:protoc-gen-grpc-java:1.50.2")
    }
}
