package com.nisecoder.helloworld.gradle

import org.gradle.kotlin.dsl.support.serviceOf
import org.gradle.tooling.events.OperationCompletionListener


serviceOf<BuildEventsListenerRegistry>().onTaskCompletion(provider { OperationCompletionListener {
//    println("${it.descriptor.name}:finish")
}})
