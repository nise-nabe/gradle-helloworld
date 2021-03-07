package com.nisecoder.helloworld.gradle

import com.google.protobuf.gradle.ExecutableLocator

plugins {
    id("com.google.protobuf")
    `java-library`
    idea
}

configurations {
    create("protoc") {
        isCanBeConsumed = false
        isCanBeResolved = true
    }
}

afterEvaluate {
    protobuf {
        protobuf.apply {
            protoc(closureOf<ExecutableLocator> {
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

            plugins(closureOf<NamedDomainObjectContainer<ExecutableLocator>> {
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

