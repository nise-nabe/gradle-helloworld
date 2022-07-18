plugins {
    `kotlin-dsl`
}

dependencies {
    implementation("com.google.protobuf:protobuf-gradle-plugin:0.8.19") {
        because("protobuf and grpc convention plugin")
    }
}

kotlinDslPluginOptions {
    jvmTarget.set(JavaVersion.VERSION_11.toString())
}
