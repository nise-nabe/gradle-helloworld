import com.nisecoder.helloworld.gradle.idea.ext.packagePrefix
import com.nisecoder.helloworld.gradle.idea.ext.settings

plugins {
    id("com.nisecoder.helloworld.gradle.build-basic")
    id("com.nisecoder.helloworld.gradle.intellij-gradle-plugin")
    id("com.nisecoder.helloworld.gradle.kotlin")
}

version = "1.0-SNAPSHOT"

intellij {
    pluginName.set("new framework")
}

idea {
    module {
        settings {
            packagePrefix["src"] = "com.nisecoder.helloworld"
        }
    }
}
