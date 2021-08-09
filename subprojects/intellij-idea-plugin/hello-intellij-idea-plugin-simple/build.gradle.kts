plugins {
    id("com.nisecoder.helloworld.gradle.intellij-gradle-plugin")
    id("com.nisecoder.helloworld.gradle.kotlin")
}

version = "1.0-SNAPSHOT"

intellij {
    pluginName.set("hello world")
}
