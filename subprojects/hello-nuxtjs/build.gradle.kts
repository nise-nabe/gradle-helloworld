plugins {
    id("com.nisecoder.helloworld.gradle.build-basic")
    id("com.nisecoder.nodejs")
    base
}

afterEvaluate {
    tasks.named("yarnTest") {
        dependsOn(tasks.named("yarnInstall"))
    }
}

tasks.check {
    dependsOn(tasks.named("yarnTest"))
}
