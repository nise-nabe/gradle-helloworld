pluginManagement {
    repositories {
        mavenCentral()
    }
}

rootProject.name = "build-platforms"

include("platform-grpc")
include("platform-helloworld")
