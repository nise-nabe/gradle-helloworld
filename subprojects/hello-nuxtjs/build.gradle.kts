plugins {
    id("com.nisecoder.helloworld.gradle.build-basic")
    id("com.nisecoder.nodejs")
    base
}

tasks.check {
    dependsOn(tasks.named("yarnTest"))
}
