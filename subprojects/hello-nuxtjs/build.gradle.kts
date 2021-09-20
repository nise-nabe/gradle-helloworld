import com.github.gradle.node.yarn.task.YarnTask

plugins {
    id("com.nisecoder.helloworld.gradle.build-basic")
    id("com.nisecoder.helloworld.gradle.nodejs")
}

node {
    download.set(true)
    version.set("16.9.1")
}

val yarnTask = tasks.named<YarnTask>("yarn") {
    if ((System.getenv("CI") ?: "") == "true") {
        args.add("--frozen-lockfile")
    }
}

val yarnNuxtBuildTask = tasks.register<YarnTask>("yarnNuxtBuild") {
    group = "nuxt"
    dependsOn(yarnTask)
    args.set(listOf("nuxt", "build"))
}

val yarnNuxtGenerateTask = tasks.register<YarnTask>("yarnNuxtGenerate") {
    group = "nuxt"
    dependsOn(yarnTask)
    args.set(listOf("nuxt", "generate"))
}

val yarnJest = tasks.register<YarnTask>("yarnJest") {
    group = "verification"
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
