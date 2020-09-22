plugins {
    `java-library`
    kotlin("jvm")
}

dependencies {
    implementation("com.squareup.okhttp3:okhttp:4.9.0")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")

    testImplementation("com.squareup.okhttp3:mockwebserver:4.9.0")
    testImplementation("com.squareup.retrofit2:retrofit-mock:2.9.0")
}
