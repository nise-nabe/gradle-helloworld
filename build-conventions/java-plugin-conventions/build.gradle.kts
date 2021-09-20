plugins {
    idea
    `kotlin-dsl-base`
    `java-gradle-plugin`
}

java {
    toolchain {
        @Suppress("MagicNumber")
        languageVersion.set(JavaLanguageVersion.of(11))
        vendor.set(JvmVendorSpec.ADOPTOPENJDK)
    }
}

idea {
    module {
        isDownloadJavadoc = true
        isDownloadSources = true
    }
}

dependencies {
    implementation("gradle.plugin.org.jetbrains.gradle.plugin.idea-ext:gradle-idea-ext:1.0")

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

    implementation("com.netflix.graphql.dgs.codegen:graphql-dgs-codegen-gradle:5.0.5") {
        because("graphql convention plugin")
    }

    implementation("com.github.node-gradle:gradle-node-plugin:3.1.0") {
        because("nodejs convention plugin")
    }

    implementation("org.asciidoctor:asciidoctor-gradle-jvm:3.3.2") {
        because("asciidoc convention plugin")
    }

    implementation("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.17.1")

    implementation("dev.fritz2:fritz2-gradle-plugin:0.11")

    implementation("net.minecraftforge.gradle:ForgeGradle:5.1.18")

    implementation("org.jetbrains.intellij.plugins:gradle-intellij-plugin:1.1.6")
    implementation("org.jetbrains.intellij.plugins:gradle-changelog-plugin:1.2.1")

    implementation("com.jetbrains.intellij.indexing.shared:cdn-layout-tool:0.8.60")
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
        register("nodejs") {
            id = "com.nisecoder.helloworld.gradle.nodejs"
            implementationClass = "com.nisecoder.helloworld.gradle.NodeJSPlugin"
        }
        register("xsd") {
            id = "com.nisecoder.helloworld.gradle.xsd"
            implementationClass = "com.nisecoder.helloworld.gradle.XSDPlugin"
        }
        register("minecraft-mod") {
            id = "com.nisecoder.helloworld.gradle.minecraft-mod"
            implementationClass = "com.nisecoder.helloworld.gradle.MinecraftModPlugin"
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
