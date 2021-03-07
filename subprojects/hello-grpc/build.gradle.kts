plugins {
    id("com.nisecoder.gradle.grpc")
}

dependencies {
    implementation(platform("com.nisecoder.helloworld:platform-grpc"))
    api("com.google.protobuf:protobuf-java")
}

configurations {
    create("protoc") {
        isCanBeConsumed = false
        isCanBeResolved = true
    }
}

dependencies {
    val protoc by configurations.getting {}
    protoc(platform("com.nisecoder.helloworld:platform-grpc"))
    protoc("com.google.protobuf:protoc")
    protoc("io.grpc:protoc-gen-grpc-java")
}

afterEvaluate {
    protobuf {
        protobuf.apply {
            protoc(closureOf<com.google.protobuf.gradle.ExecutableLocator> {
                val protocVersion = configurations.getByName("protoc")
                        .incoming.resolutionResult.allDependencies
                        .asSequence()
                        .map { it.requested }
                        .filterIsInstance<ModuleComponentSelector>()
                        .filter { it.moduleIdentifier.toString() == "com.google.protobuf:protoc" }
                        .filter { it.version.isNotEmpty() }
                        .map { it.toString() }
                        .first()
                println(protocVersion)
                artifact = protocVersion
            })

            plugins(closureOf<NamedDomainObjectContainer<com.google.protobuf.gradle.ExecutableLocator>> {
                create("grpc") {
                    val grpcPluginVersion = configurations.getByName("protoc")
                            .incoming.resolutionResult.allDependencies
                            .asSequence()
                            .map { it.requested }
                            .filterIsInstance<ModuleComponentSelector>()
                            .filter { it.moduleIdentifier.toString() == "io.grpc:protoc-gen-grpc-java" }
                            .filter { it.version.isNotEmpty() }
                            .map { it.toString() }
                            .first()
                    println(grpcPluginVersion)
                    artifact = grpcPluginVersion
                }
            })
        }
    }
}

