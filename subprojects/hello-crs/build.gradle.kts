plugins {
    `java-library`
    `java-test-fixtures`
    id("com.nisecoder.helloworld.gradle.build-basic")
    id("com.nisecoder.helloworld.gradle.kotlin")
    id("com.nisecoder.helloworld.gradle.xsd")
}

dependencies {
    implementation("org.redundent:kotlin-xml-builder:1.7.2")
    implementation("javax.xml.bind:jaxb-api:2.3.1")
}
