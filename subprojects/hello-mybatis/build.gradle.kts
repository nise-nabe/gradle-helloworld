plugins {
    java
    id("com.nisecoder.helloworld.gradle.kotlin")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:2.1.3")
}
