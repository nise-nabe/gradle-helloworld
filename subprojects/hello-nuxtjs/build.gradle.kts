import com.github.gradle.node.yarn.task.YarnTask

plugins {
    id("com.nisecoder.helloworld.gradle.build-basic")
    id("com.nisecoder.helloworld.gradle.nuxt")
}

node {
    download.set(true)
    version.set("16.13.2")
}

val yarnTask = tasks.named<YarnTask>("yarn") {
    if ((System.getenv("CI") ?: "") == "true") {
        args.add("--frozen-lockfile")
    }
}

val yarnJest = tasks.register<YarnTask>("yarnJest") {
    group = "verification"
    dependsOn(yarnTask)
    args.set(listOf("jest", "--passWithNoTests"))
}

tasks.check {
    dependsOn(yarnJest)
}
