import com.nisecoder.gradle.plugin.cidetect.isGithubActions

plugins {
    id("com.nisecoder.helloworld.gradle.build-basic")
    id("com.nisecoder.ci-detect")
    id("com.nisecoder.nodejs")
    base
}

tasks.named("yarnRunTest") {
    dependsOn(tasks.named("yarnInstall"), tasks.named("yarnRunPlaywrightDeps"))
}

if (!isGithubActions) {
    tasks.check {
        dependsOn(tasks.named("yarnRunTest"))
    }
}
