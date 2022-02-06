package com.nisecoder.helloworld.gradle.nuxt

import com.github.gradle.node.yarn.task.YarnTask
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Nested

abstract class NuxtTask: YarnTask() {
    init {
        group = "nuxt"
    }

    @get:Nested
    abstract val src: Property<NuxtSourceDirectories>
}
