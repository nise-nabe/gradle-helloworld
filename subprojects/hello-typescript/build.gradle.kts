plugins {
    kotlin("js")
}



dependencies {
    testImplementation(kotlin("test-js"))
}


kotlin {
    js(IR) {
        browser()
        compilations.all {
            compileKotlinTask.kotlinOptions.freeCompilerArgs += listOf(
                "-Xopt-in=kotlin.js.ExperimentalJsExport"
            )
        }
    }
}
