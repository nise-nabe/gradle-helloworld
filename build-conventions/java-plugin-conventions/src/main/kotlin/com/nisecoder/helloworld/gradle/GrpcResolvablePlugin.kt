package com.nisecoder.helloworld.gradle

import com.google.protobuf.gradle.ExecutableLocator
import com.google.protobuf.gradle.ProtobufConvention
import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.component.ModuleComponentSelector
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.closureOf
import org.gradle.kotlin.dsl.configure

class GrpcResolvablePlugin: Plugin<Project> {
    override fun apply(project: Project): Unit = project.run {
        configurations.create("protoc") {
            isCanBeConsumed = false
            isCanBeResolved = true
        }

        dependencies.add("protoc", dependencies.platform("com.nisecoder.helloworld:platform-grpc"))
        dependencies.add("protoc", "com.google.protobuf:protoc")
        dependencies.add("protoc", "io.grpc:protoc-gen-grpc-java")

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
    }

    fun Project.resolved(dependencyNotation: String): String {
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
}
