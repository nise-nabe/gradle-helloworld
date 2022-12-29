package com.nisecoder.helloworld.gradle

import com.google.protobuf.gradle.ProtobufExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPlugin
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.plugins.ide.idea.IdeaPlugin

class GrpcPlugin: Plugin<Project> {
    override fun apply(project: Project): Unit = project.run {
        plugins.apply("com.google.protobuf")
        plugins.apply(JavaPlugin::class)
        plugins.apply(IdeaPlugin::class)

        configure<ProtobufExtension> {
            protoc {
                artifact = "com.google.protobuf:protoc:3.17.3"
            }
            plugins {
                register("grpc") {
                    artifact = "io.grpc:protoc-gen-grpc-java:1.39.0"
                }
            }
        }
    }
}
