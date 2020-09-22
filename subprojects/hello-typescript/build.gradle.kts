plugins {
    kotlin("js")
}


kotlin {
    js(IR) {
        browser()
    }
}

dependencies {
    testImplementation(kotlin("test-js"))
}
