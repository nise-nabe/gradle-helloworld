package com.nisecoder.helloworld.gradle.nuxt

import org.gradle.api.file.DirectoryProperty
import org.gradle.api.tasks.InputFiles

interface NuxtSourceDirectories {
    @get:InputFiles
    val assets: DirectoryProperty
    @get:InputFiles
    val components: DirectoryProperty
    @get:InputFiles
    val content: DirectoryProperty
    @get:InputFiles
    val layouts: DirectoryProperty
    @get:InputFiles
    val middleware: DirectoryProperty
    @get:InputFiles
    val modules: DirectoryProperty
    @get:InputFiles
    val pages: DirectoryProperty
    @get:InputFiles
    val plugins: DirectoryProperty
    @get:InputFiles
    val static: DirectoryProperty
    @get:InputFiles
    val store: DirectoryProperty
}
