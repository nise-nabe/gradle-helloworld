import com.github.gradle.node.yarn.task.YarnTask

plugins {
    id("com.nisecoder.helloworld.gradle.build-basic")
    id("com.nisecoder.helloworld.gradle.nodejs")
}

node {
    download.set(true)
    version.set("16.13.2")
}

tasks.register<YarnTask>("yarnDev") {
    args.set(listOf("dev"))
    dependsOn(tasks.named<YarnTask>("yarn"))
}
