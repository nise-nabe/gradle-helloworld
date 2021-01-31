plugins {
    idea
}

tasks.wrapper {
    distributionType = Wrapper.DistributionType.ALL
}

allprojects {
    configurations.all {
        resolutionStrategy {
            cacheChangingModulesFor(0, TimeUnit.SECONDS)
        }
    }
}
