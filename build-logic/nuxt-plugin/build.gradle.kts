plugins {
    `kotlin-dsl`
}

dependencies {
    implementation("com.github.node-gradle:gradle-node-plugin:3.3.0") {
        because("nodejs convention plugin")
    }
}
