plugins {
    `java-library`
    id("com.nisecoder.helloworld.gradle.build-basic")
    id("com.nisecoder.helloworld.gradle.kotlin")
}

dependencies {
    implementation(platform("com.squareup.okhttp3:okhttp-bom:4.9.+")) {
    }

    implementation("com.squareup.okhttp3:okhttp")
    implementation("com.squareup.retrofit2:retrofit:2.+")

    testImplementation("com.squareup.okhttp3:mockwebserver")
    testImplementation("com.squareup.retrofit2:retrofit-mock:2.+")
}

dependencies {
    implementation("org.apache.httpcomponents.client5:httpclient5:5.0")
}

dependencies {
    implementation(platform("com.linecorp.armeria:armeria-bom:1.6.+"))
    implementation("com.linecorp.armeria:armeria")
}
