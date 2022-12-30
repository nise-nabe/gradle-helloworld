package com.nisecoder.helloworld.gradle

import org.gradle.kotlin.dsl.support.serviceOf
import org.gradle.tooling.events.FinishEvent
import org.gradle.tooling.events.OperationCompletionListener


serviceOf<BuildEventsListenerRegistry>().onTaskCompletion(provider { MyListener() })

class MyListener: OperationCompletionListener {
    override fun onFinish(event: FinishEvent) {
        logger.debug("${event.descriptor.name}:finish")
    }
}
