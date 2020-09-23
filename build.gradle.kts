plugins {
    idea

    kotlin("jvm") apply false
    kotlin("js") apply false
    kotlin("kapt") apply false
    kotlin("plugin.spring") apply false

    id("org.springframework.boot") apply false

    id("com.diffplug.spotless") apply false
}

tasks.wrapper {
    distributionType = Wrapper.DistributionType.ALL
}

allprojects {
    repositories {
        mavenCentral()
    }

    configurations.all {
        resolutionStrategy {
            cacheChangingModulesFor(0, TimeUnit.SECONDS)
        }
    }
}
