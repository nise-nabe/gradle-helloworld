package com.nisecoder.helloworld.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.build.event.BuildEventsListenerRegistry
import org.gradle.kotlin.dsl.support.serviceOf
import org.gradle.tooling.events.OperationCompletionListener

class TestServicePlugin: Plugin<Project> {
    override fun apply(project: Project) = project.run {
        serviceOf<BuildEventsListenerRegistry>()
            .onTaskCompletion(provider { OperationCompletionListener {
                logger.debug("${it.descriptor.name}:finish")
            }})
    }
}
