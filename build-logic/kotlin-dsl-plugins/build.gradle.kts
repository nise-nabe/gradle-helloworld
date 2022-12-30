plugins {
    `kotlin-dsl`
}

kotlinDslPluginOptions {
    jvmTarget.set(JavaVersion.VERSION_11.toString())
}

dependencies {
    implementation("gradle.plugin.com.github.bjornvester:xjc-gradle-plugin:1.6.0") {
        because("xsd convention plugin")
    }
    implementation("com.netflix.graphql.dgs.codegen:graphql-dgs-codegen-gradle:5.6.2") {
        because("graphql convention plugin")
    }
}
