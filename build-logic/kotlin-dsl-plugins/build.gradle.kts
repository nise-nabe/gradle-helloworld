plugins {
    `kotlin-dsl`
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(19))
        vendor.set(JvmVendorSpec.ADOPTIUM)
    }
}

dependencies {
    implementation("gradle.plugin.com.github.bjornvester:xjc-gradle-plugin:1.6.0") {
        because("xsd convention plugin")
    }
    implementation("com.netflix.graphql.dgs.codegen:graphql-dgs-codegen-gradle:5.6.2") {
        because("graphql convention plugin")
    }
    implementation("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.22.0")
    implementation("org.asciidoctor:asciidoctor-gradle-jvm:3.3.2") {
        because("asciidoc convention plugin")
    }
    implementation("com.google.protobuf:protobuf-gradle-plugin:0.9.1") {
        because("protobuf and grpc convention plugin")
    }
}
