package com.nisecoder.helloworld.gradle

import com.google.protobuf.gradle.ExecutableLocator
import com.google.protobuf.gradle.ProtobufConvention
import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.artifacts.component.ModuleComponentSelector



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
    apply(plugin = "com.google.protobuf")

    configure<ProtobufConvention> {
        protobuf.apply {
            protoc(closureOf<ExecutableLocator> {
                artifact = resolved("com.google.protobuf:protoc")
            })

            plugins(closureOf<NamedDomainObjectContainer<ExecutableLocator>> {
                create("grpc") {
                    artifact = resolved("io.grpc:protoc-gen-grpc-java")
                }
            })
        }
    }
}

fun resolved(dependencyNotation: String): String {
    return configurations.getByName("protoc")
            .incoming.resolutionResult.allDependencies
            .asSequence()
            .map { it.requested }
            .filterIsInstance<ModuleComponentSelector>()
            .filter { it.moduleIdentifier.toString() == dependencyNotation }
            .filter { it.version.isNotEmpty() }
            .map { it.toString() }
            .first()
}

