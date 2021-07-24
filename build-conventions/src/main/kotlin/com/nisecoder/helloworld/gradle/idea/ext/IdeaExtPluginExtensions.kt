package com.nisecoder.helloworld.gradle.idea.ext

import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.configure
import org.gradle.plugins.ide.idea.model.IdeaModule
import org.jetbrains.gradle.ext.ModuleSettings
import org.jetbrains.gradle.ext.PackagePrefixContainer

fun IdeaModule.settings(block: ModuleSettings.() -> Unit)
        = (this as ExtensionAware).extensions.configure(block)

fun ModuleSettings.packagePrefix(block: PackagePrefixContainer.() -> Unit)
        = (this as ExtensionAware).extensions.configure(block)
