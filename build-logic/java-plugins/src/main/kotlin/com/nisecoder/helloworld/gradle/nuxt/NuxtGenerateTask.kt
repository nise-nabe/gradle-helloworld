package com.nisecoder.helloworld.gradle.nuxt

import org.gradle.api.file.DirectoryProperty
import org.gradle.api.tasks.OutputDirectory

abstract class NuxtGenerateTask: NuxtTask() {
    @get:OutputDirectory
    abstract val outputDir: DirectoryProperty

    init {
        args.set(listOf("nuxt", "generate"))
    }
}
