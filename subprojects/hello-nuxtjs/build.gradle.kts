import com.github.gradle.node.yarn.task.YarnTask

plugins {
    id("com.nisecoder.helloworld.gradle.build-basic")
    id("com.nisecoder.helloworld.gradle.nodejs")
}

node {
    download.set(true)
    version.set("16.4.2")
}

val yarnTask = tasks.named<YarnTask>("yarn")

val yarnNuxtBuildTask = tasks.register<YarnTask>("yarnNuxtBuild") {
    dependsOn(yarnTask)
    args.set(listOf("nuxt", "build"))
}

val yarnNuxtGenerateTask = tasks.register<YarnTask>("yarnNuxtGenerate") {
    dependsOn(yarnTask)
    args.set(listOf("nuxt", "generate"))
}

val yarnJest = tasks.register<YarnTask>("yarnJest") {
    dependsOn(yarnTask)
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
