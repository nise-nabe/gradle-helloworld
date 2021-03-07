plugins {
    id("org.jetbrains.gradle.plugin.idea-ext")
}

tasks.wrapper {
    distributionType = Wrapper.DistributionType.ALL
}

allprojects {
    group = "com.nisecoder.helloworld"

    configurations.all {
        resolutionStrategy {
            cacheChangingModulesFor(0, TimeUnit.SECONDS)
        }
    }
}
