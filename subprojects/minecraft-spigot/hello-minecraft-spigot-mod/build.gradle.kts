plugins {
    `java-library`
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:1.17-R0.1-SNAPSHOT")
}

configurations.all {
    resolutionStrategy {
        cacheChangingModulesFor(31, "days")
    }
}
