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

        register("Hello2GradlePlugin") {
            id = "com.nisecoder.helloworld.hello2"
            implementationClass = "com.nisecoder.helloworld.Hello2GradlePlugin"
        }
    }
}
