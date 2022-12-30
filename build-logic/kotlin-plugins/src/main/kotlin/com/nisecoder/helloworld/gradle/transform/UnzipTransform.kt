package com.nisecoder.helloworld.gradle.transform

import org.gradle.api.artifacts.transform.InputArtifact
import org.gradle.api.artifacts.transform.TransformAction
import org.gradle.api.artifacts.transform.TransformOutputs
import org.gradle.api.artifacts.transform.TransformParameters
import org.gradle.api.file.FileSystemLocation
import org.gradle.api.file.FileType
import org.gradle.api.provider.Provider
import org.gradle.work.ChangeType
import org.gradle.work.InputChanges
import java.io.File
import java.nio.file.Files
import java.util.zip.ZipEntry
import java.util.zip.ZipException
import java.util.zip.ZipFile
import javax.inject.Inject

abstract class UnzipTransform: TransformAction<TransformParameters.None> {
    @get:Inject
    abstract val inputChanges: InputChanges

    @get:InputArtifact
    abstract val inputArtifact: Provider<FileSystemLocation>

    override
    fun transform(outputs: TransformOutputs) {
        val unzipDir = outputs.dir(inputArtifact.get().asFile.name.replace(".zip", ""))

        inputChanges.getFileChanges(inputArtifact).forEach { change ->
            val changedFile = change.file
            if (change.fileType != FileType.FILE) {
                return@forEach
            }

            when (change.changeType) {
                ChangeType.ADDED, ChangeType.MODIFIED -> unzipTo(changedFile, unzipDir)
                ChangeType.REMOVED -> unzipDir.delete()
            }
        }
    }


    private fun unzipTo(zipFile: File, unzipDir: File) {
        ZipFile(zipFile).use { zip ->
            val outputDirectoryCanonicalPath = unzipDir.canonicalPath
            for (entry in zip.entries()) {
                unzipEntryTo(unzipDir, outputDirectoryCanonicalPath, zip, entry)
            }
        }
    }

    private fun unzipEntryTo(
        outputDirectory: File,
        outputDirectoryCanonicalPath: String,
        zip: ZipFile,
        entry: ZipEntry
    ) {
        val output = outputDirectory.resolve(entry.name)
        if (!output.canonicalPath.startsWith(outputDirectoryCanonicalPath)) {
            throw ZipException("Zip entry '${entry.name}' is outside of the output directory")
        }
        if (entry.isDirectory) {
            output.mkdirs()
        } else {
            output.parentFile.mkdirs()
            zip.getInputStream(entry).use { Files.copy(it, output.toPath()) }
        }
    }
}
