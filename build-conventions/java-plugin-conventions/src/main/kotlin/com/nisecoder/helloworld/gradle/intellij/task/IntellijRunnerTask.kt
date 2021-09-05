package com.nisecoder.helloworld.gradle.intellij.task

import org.gradle.api.tasks.JavaExec

abstract class IntellijRunnerTask: JavaExec() {
    init {
        group = "intellij"
        mainClass.set("com.intellij.idea.Main")
    }
}
