plugins {
    `java-gradle-plugin`
    `kotlin-dsl-base`
}

gradlePlugin {
    plugins {
        register("HelloGradlePlugin") {
            id = "com.nisecoder.helloworld.hello"
            implementationClass = "com.nisecoder.helloworld.HelloGradlePlugin"
        }
    }
}
