plugins {
    id("com.nisecoder.helloworld.gradle.build-basic")
    id("com.nisecoder.nodejs")
    base
}

tasks.named("yarnRunTest") {
    dependsOn(tasks.named("yarnInstall"), tasks.named("yarnRunPlaywrightDeps"))
}

tasks.check {
//    dependsOn(tasks.named("yarnRunTest"))
}
