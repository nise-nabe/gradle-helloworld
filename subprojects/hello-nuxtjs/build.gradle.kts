import com.github.gradle.node.yarn.task.YarnTask

plugins {
    id("com.nisecoder.helloworld.gradle.build-basic")
    id("com.nisecoder.helloworld.gradle.nuxt")
}

node {
    download.set(true)
    version.set("16.13.2")
}


val playwrightDepsTask = tasks.register<YarnTask>("playwrightDeps") {
    args.set(listOf("playwright", "install-deps"))
    dependsOn(tasks.named("yarn"))
}

val yarnJest = tasks.register<YarnTask>("yarnJest") {
    group = "verification"
    dependsOn(tasks.named("yarn"), playwrightDepsTask)
    args.set(listOf("jest", "--passWithNoTests"))
}

tasks.check {
    dependsOn(yarnJest)
}
