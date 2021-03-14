plugins {
    `java-library`
    id("com.nisecoder.helloworld.gradle.build-basic")
    id("com.nisecoder.helloworld.gradle.kotlin")
    id("com.nisecoder.helloworld.gradle.springboot-lib")
}

sourceSets {
    create("springboot") {
        val mainSourceSet = sourceSets[SourceSet.MAIN_SOURCE_SET_NAME]
        val mainOutput = objects.fileCollection().from(mainSourceSet.output)

        compileClasspath += mainOutput
        runtimeClasspath += mainOutput

        configurations.getByName(implementationConfigurationName)
                .extendsFrom(configurations.getByName(mainSourceSet.implementationConfigurationName))
        configurations.getByName(apiConfigurationName)
                .extendsFrom(configurations.getByName(mainSourceSet.apiConfigurationName))
    }
}

java {
    registerFeature("springboot") {
        usingSourceSet(sourceSets["springboot"])
    }
}

dependencies {
    api("org.springframework:spring-web")
}

dependencies {
    val springbootApi by configurations.getting {}
    springbootApi(platform(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES))
    springbootApi("org.springframework.boot:spring-boot-starter")
}
