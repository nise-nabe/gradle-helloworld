plugins {
    `java-library`
    id("com.nisecoder.helloworld.gradle.build-basic")
    id("com.nisecoder.helloworld.gradle.kotlin")
    id("com.nisecoder.helloworld.gradle.springboot")
}


dependencies {
    implementation("com.netflix.graphql.dgs:graphql-dgs-spring-boot-starter:3.12.0")
}
