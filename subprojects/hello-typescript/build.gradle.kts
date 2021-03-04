plugins {
    kotlin("js")
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
                "-Xopt-in=kotlin.js.ExperimentalJsExport"
            )
        }
    }
}
