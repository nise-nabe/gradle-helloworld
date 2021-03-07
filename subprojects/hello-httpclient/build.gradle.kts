plugins {
    `java-library`
    id("com.nisecoder.helloworld.gradle.kotlin")
}

dependencies {
    implementation(platform("com.squareup.okhttp3:okhttp-bom:4.9.0"))

    implementation("com.squareup.okhttp3:okhttp")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")

    testImplementation("com.squareup.okhttp3:mockwebserver")
    testImplementation("com.squareup.retrofit2:retrofit-mock:2.9.0")
}
