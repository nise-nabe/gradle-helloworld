package com.nisecoder.helloworld.gradle.transform

import org.gradle.api.artifacts.transform.InputArtifact
import org.gradle.api.artifacts.transform.TransformAction
import org.gradle.api.artifacts.transform.TransformOutputs
import org.gradle.api.artifacts.transform.TransformParameters
import org.gradle.api.file.FileSystemLocation
import org.gradle.api.provider.Provider

abstract class LibDirectoryTransform : TransformAction<TransformParameters.None> {
    @get:InputArtifact
    abstract val inputArtifact: Provider<FileSystemLocation>

    override
    fun transform(outputs: TransformOutputs) {
        val dir = inputArtifact.get().asFile

        dir.resolve("lib").let { lib ->
            lib.resolve("classpath.txt").readLines().map { lib.resolve(it) }
                .filter { it.exists() }.forEach {
                    outputs.file(it)
                }
        }
    }
}
