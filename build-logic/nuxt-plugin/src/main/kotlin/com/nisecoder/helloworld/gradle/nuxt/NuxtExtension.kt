package com.nisecoder.helloworld.gradle.nuxt

import org.gradle.api.Action
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.model.ObjectFactory
import org.gradle.kotlin.dsl.newInstance

open class NuxtExtension(objects: ObjectFactory) {
    val nuxtBuild: DirectoryProperty = objects.directoryProperty()
    val dist: DirectoryProperty = objects.directoryProperty()
    val configFile: RegularFileProperty = objects.fileProperty()

    val src: NuxtSourceDirectories = objects.newInstance()

    fun src(action: Action<NuxtSourceDirectories>)
            = action.execute(src)

    fun src(block: NuxtSourceDirectories.() -> Unit)
            = src.apply(block)
}
