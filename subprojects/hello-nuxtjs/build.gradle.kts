import com.github.gradle.node.yarn.task.YarnTask

plugins {
    id("com.nisecoder.helloworld.gradle.build-basic")
    id("com.nisecoder.helloworld.gradle.nodejs")
}

node {
    download.set(true)
    version.set("16.1.0")
}

val yarnNuxtBuildTask = tasks.register<YarnTask>("yarnNuxtBuild") {
    args.set(listOf("nuxt", "build"))
}

val yarnNuxtGenerateTask = tasks.register<YarnTask>("yarnNuxtGenerate") {
    args.set(listOf("nuxt", "generate"))
}

val yarnJest = tasks.register<YarnTask>("yarnJest") {
    args.set(listOf("jest", "--passWithNoTests"))
}

tasks.build {
    dependsOn(yarnNuxtBuildTask)
}

tasks.assemble {
    dependsOn(yarnNuxtGenerateTask)
}

tasks.check {
    dependsOn(yarnJest)
}
