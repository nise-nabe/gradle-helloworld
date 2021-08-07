plugins {
    `kotlin-dsl`
}

dependencies {
    implementation("gradle.plugin.org.jetbrains.gradle.plugin.idea-ext:gradle-idea-ext:1.0")

    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin")
    implementation("org.jetbrains.kotlin:kotlin-allopen")
    implementation("org.jetbrains.kotlin:kotlin-noarg")

    implementation("org.springframework.boot:spring-boot-gradle-plugin:2.5.+") {
        because("springboot convention plugins")
    }

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

    implementation("org.jetbrains.intellij.plugins:gradle-intellij-plugin:1.1.4")
}

kotlinDslPluginOptions {
    jvmTarget.set(JavaVersion.VERSION_11.toString())
}
