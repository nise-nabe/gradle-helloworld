plugins {
    `java-library`
    `java-test-fixtures`
    id("com.nisecoder.helloworld.gradle.build-basic")
    id("com.nisecoder.helloworld.gradle.kotlin")
}

dependencies {
    implementation("org.redundent:kotlin-xml-builder:1.7.2")
}
