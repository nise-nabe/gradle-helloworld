import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    idea
    `kotlin-dsl-base`
    `java-gradle-plugin`
}

kotlin {
    jvmToolchain {
        (this as JavaToolchainSpec).languageVersion.set(JavaLanguageVersion.of(11))
        @Suppress("UnstableApiUsage")
        vendor.set(JvmVendorSpec.ADOPTIUM)
    }
}

kotlinDslPluginOptions {
    jvmTarget.set(JavaVersion.VERSION_11.toString())
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        languageVersion = "1.5"
        apiVersion = "1.5"
        javaParameters = true
    }
}

idea {
    module {
        isDownloadJavadoc = true
        isDownloadSources = true
    }
}

dependencies {
    implementation("gradle.plugin.org.jetbrains.gradle.plugin.idea-ext:gradle-idea-ext:1.+")

    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin")
    implementation("org.jetbrains.kotlin:kotlin-allopen")
    implementation("org.jetbrains.kotlin:kotlin-noarg")

    implementation("com.diffplug.spotless:spotless-plugin-gradle:5.+")

    implementation("com.google.protobuf:protobuf-gradle-plugin:0.+") {
        because("protobuf and grpc convention plugin")
    }

    implementation("gradle.plugin.com.github.bjornvester:xjc-gradle-plugin:1.6.0") {
        because("xsd convention plugin")
    }

    implementation("com.netflix.graphql.dgs.codegen:graphql-dgs-codegen-gradle:5.1.11") {
        because("graphql convention plugin")
    }

    implementation("com.github.node-gradle:gradle-node-plugin:3.3.0") {
        because("nodejs convention plugin")
    }

    implementation("org.asciidoctor:asciidoctor-gradle-jvm:3.+") {
        because("asciidoc convention plugin")
    }

    implementation("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.20.0")

    implementation("dev.fritz2:fritz2-gradle-plugin:0.13")

    implementation("org.jetbrains.intellij.plugins:gradle-intellij-plugin:1.+")
    implementation("org.jetbrains.intellij.plugins:gradle-changelog-plugin:1.+")

    implementation("com.jetbrains.intellij.indexing.shared:cdn-layout-tool:0.+")
}

gradlePlugin {
    plugins {
        register("root-build") {
            id = "com.nisecoder.helloworld.gradle.root-build"
            implementationClass = "com.nisecoder.helloworld.gradle.RootBuildPlugin"
        }
        register("build-basic") {
            id = "com.nisecoder.helloworld.gradle.build-basic"
            implementationClass = "com.nisecoder.helloworld.gradle.BuildBasicPlugin"
        }
        register("kotlin") {
            id = "com.nisecoder.helloworld.gradle.kotlin"
            implementationClass = "com.nisecoder.helloworld.gradle.KotlinPlugin"
        }
        register("graphql") {
            id = "com.nisecoder.helloworld.gradle.graphql"
            implementationClass = "com.nisecoder.helloworld.gradle.GraphQLPlugin"
        }
        register("asciidoc") {
            id = "com.nisecoder.helloworld.gradle.asciidoc"
            implementationClass = "com.nisecoder.helloworld.gradle.AsciidocPlugin"
        }
        register("code-quality") {
            id = "com.nisecoder.helloworld.gradle.code-quality"
            implementationClass = "com.nisecoder.helloworld.gradle.CodeQualityPlugin"
        }
        register("grpc") {
            id = "com.nisecoder.helloworld.gradle.grpc"
            implementationClass = "com.nisecoder.helloworld.gradle.GrpcPlugin"
        }
        register("intellij-gradle-plugin") {
            id = "com.nisecoder.helloworld.gradle.intellij-gradle-plugin"
            implementationClass = "com.nisecoder.helloworld.gradle.intellij.IntellijGradlePlugin"
        }
        register("xsd") {
            id = "com.nisecoder.helloworld.gradle.xsd"
            implementationClass = "com.nisecoder.helloworld.gradle.XSDPlugin"
        }
        register("intellij-runner") {
            id = "com.nisecoder.hello.gradle.intellij-runner"
            implementationClass = "com.nisecoder.helloworld.gradle.intellij.IntellijRunnerPlugin"
        }
        register("shared-index") {
            id = "com.nisecoder.hello.gradle.shared-index"
            implementationClass = "com.nisecoder.helloworld.gradle.intellij.IntellijSharedIndexPlugin"
        }
    }
}
