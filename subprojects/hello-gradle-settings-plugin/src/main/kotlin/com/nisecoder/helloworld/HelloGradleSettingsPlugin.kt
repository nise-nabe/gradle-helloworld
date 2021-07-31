package com.nisecoder.helloworld

import org.gradle.api.Plugin
import org.gradle.api.initialization.Settings

class HelloGradleSettingsPlugin: Plugin<Settings> {
    override fun apply(settings: Settings): Unit = settings.run {

    }
}
