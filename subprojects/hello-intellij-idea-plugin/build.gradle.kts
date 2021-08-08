plugins {
    id("com.nisecoder.helloworld.gradle.intellij-gradle-plugin")
    id("com.nisecoder.helloworld.gradle.kotlin")
}

version = "1.0-SNAPSHOT"

intellij {
    version.set("IC-2021.2")
    configureDefaultDependencies.set(false)

    pluginName.set("hello world")
    type.set("IC")
    downloadSources.set(true)
    updateSinceUntilBuild.set(true)
    sameSinceUntilBuild.set(true)
}

tasks.runIde {
    // avoid warning log
    jvmArgs("--add-exports", "java.base/jdk.internal.vm=ALL-UNNAMED")
}
