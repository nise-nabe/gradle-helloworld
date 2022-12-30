plugins {
    kotlin("js")
    id("com.nisecoder.helloworld.gradle.build-basic")

}


dependencies {
    testImplementation(kotlin("test-js"))
}


kotlin {
    js(IR) {
        browser()
        binaries.executable()
        compilations.all {
            compileKotlinTask.kotlinOptions.freeCompilerArgs += listOf(
                "-opt-in=kotlin.js.ExperimentalJsExport"
            )
        }
    }
}
