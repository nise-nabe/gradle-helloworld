import com.github.gradle.node.yarn.task.YarnTask

plugins {
    id("com.nisecoder.helloworld.gradle.build-basic")
    id("com.nisecoder.helloworld.gradle.nodejs")
}

node {
    download.set(true)
    version.set("16.1.0")
}

val yarnNuxtGenerateTask = tasks.register<YarnTask>("yarnNuxtGenerate") {
    args.set(listOf("nuxt", "generate"))
}

tasks.build {
    dependsOn(yarnNuxtGenerateTask)
}
